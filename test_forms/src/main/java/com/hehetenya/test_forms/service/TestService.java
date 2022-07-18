package com.hehetenya.test_forms.service;

import com.hehetenya.test_forms.dao.impl.DaoFactory;
import com.hehetenya.test_forms.dto.OptionDTO;
import com.hehetenya.test_forms.dto.QuestionDTO;
import com.hehetenya.test_forms.dto.TestDTO;
import com.hehetenya.test_forms.entity.Option;
import com.hehetenya.test_forms.entity.Question;
import com.hehetenya.test_forms.entity.Test;
import com.hehetenya.test_forms.exeptions.AppException;
import com.hehetenya.test_forms.exeptions.DBException;

import java.util.ArrayList;
import java.util.List;

public class TestService {


    public static List<TestDTO> getAllTests() {
        try {
            List<Test> tests = DaoFactory.getTestDao().getAll();
            List<TestDTO> testDTOS = new ArrayList<>();
            for (Test t : tests) {
                testDTOS.add(transform(t));
            }
            return testDTOS;
        }catch (DBException e){
            throw new AppException();
        }
    }

    public static TestDTO transform(Test test){
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        System.out.println("test ==>" + test.getId());
        for (Question q:
             test.getQuestions()) {
            questionDTOS.add(QuestionService.transform(q));
        }
        System.out.println("test id ==>" + test.getId());
        return new TestDTO(test.getId(),
                test.getName(),
                questionDTOS);
    }

    public static TestDTO getTest(int testId) {
        try{
            return transform(DaoFactory.getTestDao().getById(testId));
        }catch (DBException e){
            throw new AppException();
        }
    }

    public static Test transformDTO(TestDTO test) {
        List<Question> questions = new ArrayList<>();
        for (QuestionDTO q:
                test.getQuestions()) {
            questions.add(QuestionService.transformDTO(q));
        }
        return new Test(test.getId(),
                test.getName(),
                questions);
    }

    public static void createTest(TestDTO newTest) throws DBException {
        Test test = transformDTO(newTest);
        DaoFactory.getTestDao().create(test);
    }

    public static void addQuestionIntoTest(TestDTO newTest, String questionText, int points, List<String> correct, List<String> incorrect) {
        QuestionDTO newQuestion = new QuestionDTO(questionText, points, new ArrayList<>());
        for (String s: correct) {
            newQuestion.getOptions().add(new OptionDTO(s, true));
        }
        for (String s: incorrect) {
            newQuestion.getOptions().add(new OptionDTO(s, false));
        }
        newTest.getQuestions().add(newQuestion);
    }
}
