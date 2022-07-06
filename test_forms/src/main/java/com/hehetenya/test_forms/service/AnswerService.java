package com.hehetenya.test_forms.service;

import com.hehetenya.test_forms.dao.impl.DaoFactory;
import com.hehetenya.test_forms.dto.AnswerDTO;
import com.hehetenya.test_forms.entity.Answer;

import java.util.ArrayList;
import java.util.List;

public class AnswerService {

    public static AnswerDTO transform(Answer a) {
        return new AnswerDTO(a.getId(), a.getText(), a.isCorrect());
    }

    public static List<AnswerDTO> getUserAnswersheet(List<String> answerIds){
        List<AnswerDTO> answersheet = new ArrayList<>();
        for(String answerId: answerIds){
            answersheet.add(transform( DaoFactory.getAnswerDao().getById(Integer.parseInt(answerId))));
        }
        return answersheet;
    }


    public static Answer transformDTO(AnswerDTO a) {
        return new Answer(a.getId(), a.getText(), a.isCorrect());
    }
}
