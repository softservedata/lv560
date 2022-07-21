package com.onyshkevych.service.imp;

import com.onyshkevych.dao.QuestionDAO;
import com.onyshkevych.model.Question;
import com.onyshkevych.model.QuestionForm;
import com.onyshkevych.model.Quiz;
import com.onyshkevych.model.Result;
import com.onyshkevych.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.*;

@Service
public class QuestionServiceImp implements QuestionService {
    private final QuestionDAO qRepo;
    @Autowired
    QuestionForm qForm;

    @Autowired
    Question question;

    @Autowired
    public QuestionServiceImp(QuestionDAO qRepo) {
        this.qRepo = qRepo;
    }

    @Transactional
    @Override
    public String saveQuestion(Question question) {
        qRepo.saveQuestion(question);
        return "redirect:/";
    }

    @Transactional
    @Override
    public List<Question> getAllQuestions() {
        return qRepo.getAllQuestions();
    }

    @Transactional
    @Override
    public QuestionForm getQuestions() {
        List<Question> allQues = qRepo.getAllQuestions();
        for (Question que : allQues) {
            que.shuffleAnswers();
        }
        List<Question> qList = new ArrayList<Question>();

        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int rand = random.nextInt(allQues.size());
            qList.add(allQues.get(rand));
            allQues.remove(rand);
        }

        qForm.setQuestions(qList);
        return qForm;
    }


    @Transactional
    @Override
    public QuestionForm getQuestionsForQuiz() {
        List<Question> allQues = qRepo.getAllQuestions();
        qForm.setQuestions(allQues);
        return qForm;
    }

    @Transactional
    @Override
    public String checkAnsOption(String optionA, String optionB, String optionC, String ans) {
        System.out.println(ans);
        if (Objects.equals(ans, "1")) {
            ans = optionA;
        } else if (Objects.equals(ans, "2")) {
            ans = optionB;
        } else if (Objects.equals(ans, "3")) {
            ans = optionC;
        } else {
            ans = "none";
        }
        return ans;
    }

    @Transactional
    @Override
    public Question createQuestion(String title, String optionA, String optionB, String optionC, String ans) {
        String chose = "-1";
        String questionType = "single";
        List<Question> allQues = qRepo.getAllQuestions();
        int id = allQues.size() + 1;
        Question newQuestion = new Question(id, title, optionA, optionB, optionC, ans, chose, questionType);
        qRepo.saveQuestion(newQuestion);
        return newQuestion;
    }

    @Transactional
    @Override
    public String startQuiz(String name, String password, Result result, Model m, boolean submitted) {
        submitted = false;
        if (name != null) result.setUsername(name);
        else result.setUsername("unregistered");
        QuestionForm qForm = getQuestions();
        m.addAttribute("qForm", qForm);
        return "quiz";

    }

    @Transactional
    @Override
    public String createQue(String title, String optionA, String optionB, String optionC, String ans) {
        String answer = checkAnsOption(optionA, optionB, optionC, ans);
        createQuestion(title, optionA, optionB, optionC, answer);
        return "redirect:/";

    }

    @Transactional
    @Override
    public void saveQuiz(Quiz quiz) {
        qRepo.saveQuiz(quiz);
    }

    @Transactional
    @Override
    public List<Quiz> getAllQuiz() {
        return qRepo.getAllQuiz();
    }

    @Transactional
    @Override
    public Question getQuestionById(Integer ques_id) {
        return qRepo.getQuestionById(ques_id);
    }

    @Transactional
    @Override
    public Quiz getQuizById(Integer idquiz) {
        return qRepo.getQuizById(idquiz);
    }

    @Transactional
    @Override
    public String getQuestionsPage(Model m) {
        QuestionForm qForm = getQuestionsForQuiz();
        m.addAttribute("qForm", qForm);
        return "quiz_creation";
    }

    @Transactional
    @Override
    public String submitQuiz(QuestionForm qForm, String title) {
        List<Question> list = qForm.getQuestions();
        List<String> ids = new ArrayList<>();
        for (Question que : list) {
            if (que.getChose() != null) {
                String id = que.getChose();
                ids.add(id);
            }
        }
        if (ids.size() == 5) {
            Quiz q = new Quiz(ids, title);
            saveQuiz(q);
            return "redirect:/";
        }
        return "redirect:/admin/questions";
    }

    @Transactional
    @Override
    public String getQuiz(Integer idquiz,
                          String name,
                          Model m,
                          Result result,
                          boolean submitted){
        Quiz quiz = getQuizById(idquiz);
        List<String> ques = quiz.putQues();
        List<Question> question_list = new ArrayList<>();
        for (String a : ques) {
            int b = Integer.parseInt(a);
            Question question = getQuestionById(b);
            question.shuffleAnswers();
            question_list.add(question);
        }
        QuestionForm qForm = new QuestionForm();
        result.setUsername(name);
        submitted = false;
        qForm.setQuestions(question_list);
        m.addAttribute("qForm", qForm);
        return "quiz";
    }
    @Transactional
    @Override
    public String seeQuiz(Model model){
        List<Quiz> quizList = getAllQuiz();
        model.addAttribute("quizList", quizList);
        return "quiz_list";
    }
}



