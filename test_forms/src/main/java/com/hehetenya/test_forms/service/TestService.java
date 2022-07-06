package com.hehetenya.test_forms.service;

import com.hehetenya.test_forms.dao.impl.DaoFactory;
import com.hehetenya.test_forms.dto.AnswerDTO;
import com.hehetenya.test_forms.dto.QuestionDTO;
import com.hehetenya.test_forms.dto.TestDTO;
import com.hehetenya.test_forms.entity.Answer;
import com.hehetenya.test_forms.entity.Question;
import com.hehetenya.test_forms.entity.Test;
import com.hehetenya.test_forms.exeptions.DBException;

import java.util.ArrayList;
import java.util.List;

public class TestService {


    public static List<TestDTO> getAllTests() {
        List<Test> tests = DaoFactory.getTestDao().getAll();
        List<TestDTO> testDTOS = new ArrayList<>();
        for (Test t: tests) {
            testDTOS.add(transform(t));
        }
        return testDTOS;
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
                test.getDurationMinutes(),
                test.getCreator().getLogin(),
                questionDTOS);
    }

    public static TestDTO getTest(int testId) {
        System.out.println(DaoFactory.getTestDao().getById(testId).getName() + "<=== test name");
        return transform(DaoFactory.getTestDao().getById(testId));
    }

    public static Test transformDTO(TestDTO test) {
        List<Question> questions = new ArrayList<>();
        for (QuestionDTO q:
                test.getQuestions()) {
            questions.add(QuestionService.transformDTO(q));
        }
        return new Test(test.getId(),
                test.getName(),
                test.getDurationMinutes(),
                questions);
    }

    public static void createTest(TestDTO newTest) throws DBException {
        Test test = transformDTO(newTest);
        for (Answer a: test.getQuestions().get(0).getAnswers()) {
            System.out.println("first question ==>" + a.getText());
        }
        DaoFactory.getTestDao().create(test);
    }
}
