package com.hehetenya.test_forms.service;

import com.hehetenya.test_forms.dto.AnswerDTO;
import com.hehetenya.test_forms.dto.QuestionDTO;
import com.hehetenya.test_forms.entity.Answer;
import com.hehetenya.test_forms.entity.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionService {

    public static QuestionDTO transform(Question question) {
        List<AnswerDTO> answerDTOS = new ArrayList<>();
        for (Answer a:
                question.getAnswers()) {
            answerDTOS.add(AnswerService.transform(a));
        }
        return new QuestionDTO(question.getText(), question.getPoints(), answerDTOS);
    }

    public static Question transformDTO(QuestionDTO question) {
        List<Answer> answers = new ArrayList<>();
        for (AnswerDTO a:
                question.getAnswers()) {
            answers.add(new Answer(a.getText(), a.isCorrect()));
        }
        return new Question(question.getText(), question.getPoints(), answers);
    }

}
