package com.hehetenya.test_forms.service;

import com.hehetenya.test_forms.dto.OptionDTO;
import com.hehetenya.test_forms.dto.QuestionDTO;
import com.hehetenya.test_forms.entity.Option;
import com.hehetenya.test_forms.entity.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionService {

    public static QuestionDTO transform(Question question) {
        List<OptionDTO> optionDTOS = new ArrayList<>();
        for (Option a:
                question.getAnswers()) {
            optionDTOS.add(AnswerService.transform(a));
        }
        return new QuestionDTO(question.getText(), question.getPoints(), optionDTOS);
    }

    public static Question transformDTO(QuestionDTO question) {
        List<Option> options = new ArrayList<>();
        for (OptionDTO o:
                question.getOptions()) {
            options.add(new Option(o.getText(), o.isCorrect()));
        }
        return new Question(question.getText(), question.getPoints(), options);
    }

}
