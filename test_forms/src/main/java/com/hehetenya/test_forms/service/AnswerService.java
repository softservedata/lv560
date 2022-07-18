package com.hehetenya.test_forms.service;

import com.hehetenya.test_forms.dao.impl.DaoFactory;
import com.hehetenya.test_forms.dto.OptionDTO;
import com.hehetenya.test_forms.entity.Option;
import com.hehetenya.test_forms.exeptions.AppException;
import com.hehetenya.test_forms.exeptions.DBException;

import java.util.ArrayList;
import java.util.List;

public class AnswerService {

    public static OptionDTO transform(Option a) {
        return new OptionDTO(a.getId(), a.getText(), a.isCorrect());
    }

    public static List<OptionDTO> getUserAnswers(List<String> answerIds){
        List<OptionDTO> answers = new ArrayList<>();
        try{
            for(String answerId: answerIds){
                answers.add(transform( DaoFactory.getAnswerDao().getById(Integer.parseInt(answerId))));
            }
        }catch (DBException e){
            throw new AppException();
        }
        return answers;
    }


    public static Option transformDTO(OptionDTO a) {
        return new Option(a.getId(), a.getText(), a.isCorrect());
    }
}
