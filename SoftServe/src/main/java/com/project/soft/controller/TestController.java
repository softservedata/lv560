package com.project.soft.controller;

import com.project.soft.entity.*;
import com.project.soft.security.CustomUserDetails;
import com.project.soft.service.*;
import com.project.soft.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.project.soft.config.ApplicationParameters.*;
import static com.project.soft.util.CompletedQuestionStatus.*;
import static com.project.soft.util.TestValidator.*;


@RequiredArgsConstructor
@RequestMapping(path = "/tests")
@Controller
public class TestController {

    private final TestService testService;
    private final QuestionTypeService questionTypeService;
    private final QuestionService questionService;
    private final ResultService resultService;
    private final UserService userService;
    private final TopicService topicService;
    private final CategoryService categoryService;
    private final SubscriptionService subscriptionService;

    @Scheduled(fixedDelay = 1000 * 3600 * 24)
    public void updateTestsStatus(){
        List<Test> tests = testService.findAll();
        LocalDate currentDate = LocalDate.now();

        for(Test test : tests) {
            boolean isActive = test.getGroups().stream()
                    .anyMatch(x -> {
                        LocalDate startDate = x.getStartDate();
                        LocalDate endDate = x.getEndDate();

                        return currentDate.isAfter(startDate) &&
                               currentDate.isBefore(endDate);
                    });

            test.setActive(isActive);
            testService.save(test);
        }
    }

    @GetMapping("/create")
    public String createTestPage(HttpSession session, Model model) {

        if (session.getAttribute("test") == null)
            session.setAttribute("test", new Test());

        if (session.getAttribute("answerCounter") == null)
            session.setAttribute("answerCounter", 3);

        if (session.getAttribute("errors") == null){
            resetErrors(session);
        }

        List<Topic> topics = topicService.findAllTopics();
        List<Category> categories = categoryService.findAllCategories();

        String updatedQuestionTitle = (String) session.getAttribute("updatedQuestion");
        String addedQuestionTitle = (String) session.getAttribute("addedQuestion");
        BindingResult errors = (BindingResult) session.getAttribute("errors");
        BindingResult updErrors = (BindingResult) session.getAttribute("updErrors");
        Test test = (Test) session.getAttribute("test");
        int integer = (Integer) session.getAttribute("answerCounter");

        InputQuestionContainer container = new InputQuestionContainer();

        for (int i = 0; i < integer; i++) {
            container.getAnswerTitles().add("");
        }

        Set<Question> questions = test.getQuestions();

        model.addAttribute("categories", categories);
        model.addAttribute("topics", topics);
        model.addAttribute("questions", questions);
        model.addAttribute("errors", errors);
        model.addAttribute("updErrors", updErrors);
        model.addAttribute("archivedTests", testService.findArchivedTests());
        model.addAttribute("unarchivedTests", testService.findUnarchivedTests());
        model.addAttribute("questionContainer", container);
        model.addAttribute("updatedQuestion", updatedQuestionTitle);
        model.addAttribute("addedQuestion", addedQuestionTitle);

        return "new_test";
    }

    @GetMapping("/category/{id}")
    public String testsByTopicPage(@PathVariable Long id, Model model, HttpSession session) {
        User user = getAuthenticatedUser();
        Long userId = user.getId();
        Set<Test> tests = testService.findTestsByTopic(id);
        Map<Long, Like> testLiked = new HashMap<>();
        Topic topic = topicService.findById(id);

        for (Test test : tests) {
            Set<Like> likes = test.getLikes();
            Like like = likes.stream()
                    .filter(x -> x.getUser().getId().equals(userId))
                    .findAny()
                    .orElse(null);

            testLiked.put(test.getId(), like);
        }

        model.addAttribute("testLiked", testLiked);
        model.addAttribute("user", user);
        model.addAttribute("tests", tests);
        model.addAttribute("topic", topic);

        session.setAttribute("test", null);
        session.setAttribute("errors", null);

        return "categories";
    }

    @GetMapping("/{id}")
    public String startTest(@PathVariable Long id, HttpSession session) {
        Test test = testService.findTestById(id);
        Map<Integer, Boolean> indexCheckedQuestionMap = new HashMap<>();
        InputResultData inputResultData = new InputResultData();
        List<Question> questions = questionService.findQuestionsByTest(test);
        Long questionId;

        for (int i = 0; i < questions.size(); i++) {
            indexCheckedQuestionMap.put(i, false);
        }

        shuffle(questions);

        questionId = questions.get(0).getId();

        session.setAttribute("currentTest", test);
        session.setAttribute("questions", questions);
        session.setAttribute("indexCheckedQuestionMap", indexCheckedQuestionMap);
        session.setAttribute("questionIndex", 0);
        session.setAttribute("currentResult", inputResultData);

        return String.format("redirect:/tests/question/%d", questionId);
    }

    @SuppressWarnings("unchecked")
    @GetMapping(path = {"/question/{questionId}", "/question/{questionId}/{paramIndex}"})
    public String questionPage(@PathVariable Long questionId,
                               @PathVariable(required = false) Integer paramIndex,
                               Model model,
                               HttpSession session) {

        Integer index;

        if (paramIndex != null) {
            index = paramIndex;
            session.setAttribute("questionIndex", index);
        } else {
            index = (Integer) session.getAttribute("questionIndex");
        }

        List<Question> questions = (List<Question>) session.getAttribute("questions");
        Map<Integer, Boolean> map = (Map<Integer, Boolean>) session.getAttribute("indexCheckedQuestionMap");
        Question question = questions.get(index);
        Test test = (Test) session.getAttribute("currentTest");
        Map<Answer, Boolean> answerCheckedMap = new HashMap<>();
        Integer nextIndex = index == (questions.size() - 1) ? null : index+1;
        Integer prevIndex = index == 0 ? null : index-1;

        Boolean isChecked = map.get(index);

        if (isChecked) {
            InputResultData resultData = (InputResultData) session.getAttribute("currentResult");
            InputAnswersContainer container = resultData.getInputAnswersContainers().stream()
                    .filter(x -> x.getQuestionId().equals(questionId))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException(
                            String.format("Question with id '%d' isn't passed", questionId)
                    ));

            List<Long> answerIds = new ArrayList<>();

            if (container.getQuestionType().toString().equals("radio")) {
                answerIds.add(container.getAnswerId());
            } else {
                for (int j = 0; j < MAX_VARIANTS; j++) {
                    Long id = container.getAnswerIds()[j];

                    if (id == null) break;

                    answerIds.add(id);
                }
            }

            for (Answer answer: question.getAnswers()) {
                boolean checked = false;
                if (answerIds.contains(answer.getId())) {
                    checked = true;
                }
                answerCheckedMap.put(answer, checked);
            }

        } else {
            for (Answer answer: question.getAnswers()) {
                answerCheckedMap.put(answer, false);
            }
        }

        InputAnswersContainer container = new InputAnswersContainer();
        model.addAttribute("test", test);
        model.addAttribute("container", container);
        model.addAttribute("question", question);
        model.addAttribute("questionTitle", question.getTitle());
        model.addAttribute("answerCheckedMap", answerCheckedMap);
        model.addAttribute("nextIndex", nextIndex);
        model.addAttribute("prevIndex", prevIndex);

        return "test";
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/finish")
    public String preFinishPage(HttpSession session, Model model) {
        List<Question> questions = (List<Question>) session.getAttribute("questions");
        Map<Integer, Boolean> map = (Map<Integer, Boolean>) session.getAttribute("indexCheckedQuestionMap");

        model.addAttribute("questions", questions);
        model.addAttribute("indexCheckedQuestionMap", map);

        return "finish";
    }

    @GetMapping("/{id}/users")
    public String usersPage(@PathVariable Long id, Model model) {
        Test test = testService.findTestById(id);
        List<User> users = userService.findUsersByTest(test);

        model.addAttribute("user", getAuthenticatedUser());
        model.addAttribute("users", users);
        model.addAttribute("test", test);

        return "users";
    }

    @GetMapping(path = {"/{id}/results", "/{id}/results/user/{userId}"})
    public String resultsTestPage(@PathVariable Long id,
                                  @PathVariable(required = false) Long userId,
                                  Model model) {
        Test test = testService.findTestById(id);
        User user;
        boolean original = false;
        boolean hasSubscription = false;

        if (userId != null) {
            user = userService.findUserById(userId);
        } else {
            original = true;
            user = getAuthenticatedUser();
        }

        if (original) {
            List<Group> groups = subscriptionService.findALlByUserId(user.getId()).stream()
                    .map(Subscription::getGroup)
                    .collect(Collectors.toList());

            Set<Group> testGroups = test.getGroups();

            hasSubscription = groups.stream()
                    .anyMatch(testGroups::contains);
        }

        List<Result> results = resultService.findAllByUserAndTest(user, test);

        model.addAttribute("hasSubscription", hasSubscription);
        model.addAttribute("user", getAuthenticatedUser());
        model.addAttribute("test", test);
        model.addAttribute("results", results);
        model.addAttribute("original", original);

        return "results";
    }

    @GetMapping("/{id}/result/{resultId}")
    public String resultTestPage(@PathVariable Long id,
                                 @PathVariable Long resultId,
                                 Model model) {

        OutputResultData outputResultData = new OutputResultData();

        Test test = testService.findTestById(id);
        Result result = resultService.findResultById(resultId);
        List<Question> questions = questionService.findQuestionsByTest(test);
        List<QuestionHistory> qHistories = result.getQuestionHistories();

        for (Question question : questions) {
            OutputAnswerContainer answerContainer = new OutputAnswerContainer();
            int value = 0;
            int correctAmount = 0;
            int correctSelectedAmount = 0;

            List<Long> selectedQuestionIds = new ArrayList<>();
            List<Answer> answers = question.getAnswers();
            List<Long> questionAnswerIds = answers.stream()
                    .map(Answer::getId)
                    .collect(Collectors.toList());

            qHistories.stream()
                    .map(QuestionHistory::getAnswer)
                    .map(Answer::getId)
                    .filter(questionAnswerIds::contains)
                    .forEach(selectedQuestionIds::add);

            for (Answer answer: answers) {
                boolean isCorrect = answer.getCorrect();
                boolean isSelected = false;

                if (isCorrect)
                    correctAmount++;

                if (selectedQuestionIds.contains(answer.getId())) {
                    isSelected = true;

                    if (isCorrect) {
                        value += answer.getValue();
                        correctSelectedAmount++;
                    } else {
                        value -= answer.getValue();
                    }
                }

                answerContainer.put(answer, isSelected);
            }

            answerContainer.setQuestion(question);
            answerContainer.setValue(Math.max(value, 0));

            if (question.getType().toString().equals("radio")) {

                if (correctSelectedAmount > 0) {
                    answerContainer.setStatus(CORRECT);
                } else {
                    answerContainer.setStatus(INCORRECT);
                }
            } else if (question.getType().toString().equals("checkbox")) {

                if (correctSelectedAmount == correctAmount) {
                    answerContainer.setStatus(CORRECT);
                } else if (correctSelectedAmount == 0) {
                    answerContainer.setStatus(INCORRECT);
                } else {
                    answerContainer.setStatus(PARTIALLY_CORRECT);
                }
            }

            outputResultData.add(answerContainer);
        }
        List<OutputAnswerContainer> containers = outputResultData.getOutputAnswerContainers();

        model.addAttribute("user", getAuthenticatedUser());
        model.addAttribute("answerContainers", containers);
        model.addAttribute("test", test);

        return "test_result";
    }

    @SuppressWarnings("unchecked")
    @PostMapping("/question/{questionId}")
    public String saveQuestionAnswers(@PathVariable Long questionId,
                                      @RequestParam(required = false) String questionType,
                                      @RequestParam(required = false) String refType,
                                      @RequestParam(required = false) String[] answerIds,
                                      @RequestParam(required = false) String answerId,
                                      @ModelAttribute InputAnswersContainer container,
                                      HttpSession session) {

        Integer index = (Integer) session.getAttribute("questionIndex");
        List<Question> questions = (List<Question>) session.getAttribute("questions");
        InputResultData resultData = (InputResultData) session.getAttribute("currentResult");

        if (answerId != null) {
            container.setAnswerId(Long.parseLong(answerId));
        }

        if (answerIds != null) {
            for (String id : answerIds) {
                container.addId(Long.parseLong(id));
            }
        }

        container.setQuestionId(questionId);
        container.setQuestionType(new QuestionType(questionType));
        resultData.addContainer(container);

        if (isChecked(answerId, answerIds)) {
            Map<Integer, Boolean> map = (Map<Integer, Boolean>) session.getAttribute("indexCheckedQuestionMap");
            map.replace(index, true);
        }

        String moveToQuestionPath = "redirect:/tests/question/";

        if (refType.equals("prev")) {
            moveToQuestionPath += questions.get(--index).getId();
            session.setAttribute("questionIndex", index);
        } else if (refType.equals("next")) {
            moveToQuestionPath += questions.get(++index).getId();
            session.setAttribute("questionIndex", index);
        } else {
            moveToQuestionPath = "redirect:/tests/finish";
        }

        return moveToQuestionPath;
    }

    @SuppressWarnings("unchecked")
    @PostMapping("/save_result")
    public String saveFinalResult(@RequestParam String refType, HttpSession session) {
        InputResultData resultData = (InputResultData) session.getAttribute("currentResult");
        List<Question> questions = (List<Question>) session.getAttribute("questions");
        Test test = (Test) session.getAttribute("currentTest");

        String moveToPath;

        if (refType.equals("finish")) {
            moveToPath = String.format("redirect:/tests/%d/results", test.getId());
            test.increasePassageCounter();
            createResultAndHistory(resultData, questions, test);
        } else {
            Integer index = (Integer) session.getAttribute("questionIndex");
            Long questionId = questions.get(index).getId();
            moveToPath = String.format("redirect:/tests/question/%d", questionId);
        }

        return moveToPath;
    }

    @PostMapping("/import_test")
    public String importTest(@RequestParam(required = false) List<Long> unarchivedIds,
                             @RequestParam(required = false) List<Long> archivedIds,
                             HttpSession session) {

        resetErrors(session);

        Test test = (Test) session.getAttribute("test");
        List<Long> testIds = new ArrayList<>();

        if (archivedIds != null)
            testIds.addAll(archivedIds);

        if (unarchivedIds != null)
            testIds.addAll(unarchivedIds);

        if (testIds.size() == 0) return "redirect:tests/create";

        List<Test> tests = testService.findALlByIds(testIds);

        for (Test t : tests) {
            List<Question> testQuestions = questionService.findQuestionsByTest(t);
            testQuestions.forEach(test::addQuestion);
        }

        return "redirect:/tests/create";
    }

    @PostMapping("/archive/{id}")
    public String archiveTest(@PathVariable Long id) {
        Test test = testService.findTestById(id);
        test.setArchived(true);
        testService.save(test);

        return "redirect:/home";
    }

    @PostMapping("/restore/{id}")
    public String restoreTest(@PathVariable Long id) {
        Test test = testService.findTestById(id);
        test.setArchived(false);
        testService.save(test);

        return "redirect:/archived";
    }

    @PostMapping
    public String createTest(@RequestParam(required = false) String testName,
                             @RequestParam(required = false) List<Long> topicIds,
                             @RequestParam String pictureUrl,
                             @RequestParam String description,
                             HttpSession session) {

        Test test = (Test) session.getAttribute("test");
        resetErrors(session);
        BindingResult errors = (BindingResult) session.getAttribute("errors");

        if (!isValidTest(testName, test)) {
            if (errors == null) {
                errors = new BeanPropertyBindingResult(new InputQuestionContainer(), "questionContainer");
                session.setAttribute("errors", errors);
            }

            validateTest(testName, test, errors);

            return "redirect:/tests/create";
        }

        test.setTitle(testName);
        test.setDateOfCreation(LocalDate.now());
        test.setCreator(getAuthenticatedUser());
        test.setDescription(description);
        test.setPictureUrl(pictureUrl);
        Test savedTest = testService.saveTest(test);

        topicService.updateTopics(topicIds, savedTest);

        return "redirect:/home";
    }

    @PostMapping("/add_question")
    public String addQuestion(@ModelAttribute @Valid InputQuestionContainer container,
                              BindingResult errors,
                              @RequestParam(required = false) String[] correct,
                              @RequestParam(required = false) Long categoryId,
                              HttpSession session) {

        session.setAttribute("answerCounter", MIN_VARIANTS);
        resetErrors(session);

        List<String> answerTitles = container.getAnswerTitles();
        Test test = (Test) session.getAttribute("test");

        validateAnswers(answerTitles, correct, errors);

        if (!container.getQuestionTitle().isEmpty()) {
            validateQuestion(test, null, container.getQuestionTitle(), categoryId, errors);
        }

        if (errors.hasErrors()) {
            session.setAttribute("errors", errors);
            session.setAttribute("addedQuestion", container.getQuestionTitle());
            return "redirect:/tests/create";
        }

        Category category = categoryService.findById(categoryId);
        container.setCategory(category);

        addQuestionUtil(container, correct, session);

        return "redirect:/tests/create";
    }

    @PostMapping("/add_answer")
    public String addAnswer(HttpSession session) {
        int counter = (int) session.getAttribute("answerCounter");
        resetErrors(session);

        if (++counter > MAX_VARIANTS) {
            BindingResult errors = (BindingResult) session.getAttribute("errors");
            FieldError error = new FieldError(
                    "questionContainer",
                    "answers",
                    String.format(
                            "The test should contain no more than %d answer options",
                            MAX_VARIANTS
                    )
            );
            errors.addError(error);

            return "redirect:/tests/create";
        }

        session.setAttribute("answerCounter", counter);

        return "redirect:/tests/create";
    }

    @PatchMapping("/update/{title}")
    public String updateQuestion(@RequestParam String questionTitle,
                                 @RequestParam(required = false) String[] answers,
                                 @RequestParam(required = false) String[] correct,
                                 @RequestParam(required = false) Integer value,
                                 @RequestParam(required = false) Long categoryId,
                                 @PathVariable String title,
                                 HttpSession session) {

        InputQuestionContainer container = new InputQuestionContainer();
        Test test = (Test) session.getAttribute("test");

        BindingResult errors = new BeanPropertyBindingResult(
                new InputQuestionContainer(),
                "updateContainer");
        session.setAttribute("updErrors", errors);

        List<String> answerTitles = Arrays.asList(answers);
        validateQuestion(answerTitles, correct, value, questionTitle, categoryId, errors);

        if (!questionTitle.isEmpty()) {
            validateQuestion(test, title, questionTitle, categoryId, errors);
        }

        container.setAnswerTitles(answerTitles);
        container.setValue(value);
        container.setQuestionTitle(questionTitle);

        if (errors.hasErrors()) {
            session.setAttribute("updateErrors", errors);
            session.setAttribute("updatedQuestion", title);
            return "redirect:/tests/create";
        }

        Category category = categoryService.findById(categoryId);
        container.setCategory(category);

        deleteQuestion(session, title);
        addQuestionUtil(container, correct, session);

        return "redirect:/tests/create";
    }

    @DeleteMapping("/drop_answer")
    public String dropAnswer(HttpSession session) {
        int counter = (int) session.getAttribute("answerCounter");
        resetErrors(session);

        if (--counter < MIN_VARIANTS) {
            BindingResult errors = (BindingResult) session.getAttribute("errors");
            FieldError error = new FieldError(
                    "questionContainer",
                    "answers",
                    String.format(
                            "The test should contain at least %d answer options",
                            MIN_VARIANTS
                    )
            );
            errors.addError(error);

            return "redirect:/tests/create";
        }
        session.setAttribute("answerCounter", counter);

        return "redirect:/tests/create";
    }

    @DeleteMapping("/delete/{title}")
    public String deleteQuestion(HttpSession session, @PathVariable String title) {
        Test test = (Test) session.getAttribute("test");
        Iterator<Question> iterator = test.getQuestions().iterator();

        while (iterator.hasNext()) {
            if (iterator.next().getTitle().equals(title)) {
                iterator.remove();
                return "redirect:/tests/create";
            }
        }

        throw new IllegalArgumentException(
                String.format("There's no question '%s'", title)
        );
    }

    private void resetErrors(HttpSession session) {
        BindingResult errors = new BeanPropertyBindingResult(
                new InputQuestionContainer(),
                "questionContainer"
        );
        BindingResult updErrors = new BeanPropertyBindingResult(
                new InputQuestionContainer(),
                "questionContainer"
        );
        session.setAttribute("errors", errors);
        session.setAttribute("updErrors", updErrors);
    }

    private void addQuestionUtil(InputQuestionContainer container,
                                 String[] correct,
                                 HttpSession session) {

        List<String> answerTitles = container.getAnswerTitles();
        List<String> correctList = Arrays.asList(correct);
        int answerValue = container.getValue() / correct.length;

        Test test = (Test) session.getAttribute("test");

        Question question = new Question();
        question.setCategory(container.getCategory());
        question.setTitle(container.getQuestionTitle());
        question.setType(findQuestionType(correct));

        for (int i = 0; i < answerTitles.size(); i++) {
            Answer answer = new Answer();
            answer.setValue(answerValue);
            answer.setText(answerTitles.get(i));
            answer.setCorrect(correctList.contains(Integer.toString(i)));

            question.addAnswer(answer);
        }

        test.addQuestion(question);
    }

    private QuestionType findQuestionType(String[] correct) {
        if (correct.length == 0)
            throw new IllegalArgumentException("There must be at least one correct answer");
        else if (correct.length == 1) {
            return questionTypeService.findTypeByName("radio");
        } else {
            return questionTypeService.findTypeByName("checkbox");
        }
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getUser();
    }

    private void shuffle(List<Question> questions) {
        Collections.shuffle(questions);

        for (Question question: questions) {
            Collections.shuffle(question.getAnswers());
        }
    }

    private void createResultAndHistory(InputResultData inputResultData,
                                        List<Question> questions,
                                        Test test) {

        List<InputAnswersContainer> inputAnswersContainers =
                inputResultData.getInputAnswersContainers();
        Result result = new Result();
        int grade = 0;

        for (int i = 0; i < inputAnswersContainers.size(); i++) {
            Question question = questions.get(i);
            List<Answer> answers = question.getAnswers();
            List<Long> answerIds = new ArrayList<>();
            int questionGrade = 0;

            if (question.getType().toString().equals("radio")) {
                answerIds.add(inputAnswersContainers.get(i).getAnswerId());
            } else {
                for (int j = 0; j < MAX_VARIANTS; j++) {
                    Long id = inputAnswersContainers.get(i).getAnswerIds()[j];

                    if (id == null) break;

                    answerIds.add(id);
                }
            }

            List<Answer> selectedAnswers = answers.stream()
                    .filter(x -> answerIds.contains(x.getId()))
                    .collect(Collectors.toList());

            for (Answer answer : selectedAnswers) {
                QuestionHistory history = new QuestionHistory();
                history.setAnswer(answer);

                if (answer.getCorrect())
                    questionGrade += answer.getValue();
                else
                    questionGrade -= answer.getValue();

                result.addQuestionHistory(history);
            }

            grade += Math.max(questionGrade, 0);
        }

        result.setUser(getAuthenticatedUser());
        result.setGrade(grade);
        result.setTest(test);
        result.setTestFinishTime(LocalDateTime.now());

        testService.save(test);
        resultService.save(result);
    }

    private boolean isChecked(String id, String[] ids) {
        return (id == null && ids != null) || (id != null && ids == null);
    }
}