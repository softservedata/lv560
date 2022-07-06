package com.hehetenya.test_forms.service;

import com.hehetenya.test_forms.dao.impl.DaoFactory;
import com.hehetenya.test_forms.dto.*;
import com.hehetenya.test_forms.entity.Answer;
import com.hehetenya.test_forms.entity.Result;

import java.util.ArrayList;
import java.util.List;

public class ResultService {
    public static List<ResultDTO> getAllResults() {
        List<Result> results = DaoFactory.getResultDao().getAll();
        List<ResultDTO> resultDTOS = new ArrayList<>();
        for (Result r: results) {
            resultDTOS.add(transform(r));
        }
        return resultDTOS;
    }

    public static ResultDTO transform(Result result){
        List<AnswerDTO> answerDTOS = new ArrayList<>();
        for (Answer a:
                result.getAnswersheet()) {
            answerDTOS.add(AnswerService.transform(a));
        }
        return new ResultDTO(result.getId(),
                UserService.transform(result.getUser()),
                TestService.transform(result.getTest()),
                result.getGrade(),
                answerDTOS);
    }


    public static List<ResultDTO> getAllResultsOfAUser(UserDTO user) {
        List<ResultDTO> resultDTOS = getAllResults();
        System.out.println("in result service, result DTO size ==> "+ resultDTOS.size());
        resultDTOS.removeIf(r -> !r.getUser().getLogin().equals(user.getLogin()));
        return resultDTOS;
    }


    public static void sendNewResult(ResultDTO resultDTO) {
        List<Answer> answersheet = new ArrayList<>();
        for(AnswerDTO a: resultDTO.getAnswersheet()){
            answersheet.add(AnswerService.transformDTO(a));
        }
        DaoFactory.getResultDao().create(new Result(UserService.transformDTO(resultDTO.getUser()),
                TestService.transformDTO(resultDTO.getTest()),
                resultDTO.getGrade(),
                answersheet));
    }


    public static Result transformDTO(ResultDTO result){
        List<Answer> answers = new ArrayList<>();
        for (AnswerDTO a:
                result.getAnswersheet()) {
            answers.add(AnswerService.transformDTO(a));
        }
        return new Result(UserService.transformDTO(result.getUser()),
                TestService.transformDTO(result.getTest()),
                result.getGrade(),
                answers);
    }

    public static ResultDTO getResultById(int resultId) {
        List<ResultDTO> resultDTOS = getAllResults();
        for(ResultDTO r: resultDTOS){
            if(r.getId() == resultId){
                return r;
            }
        }
        System.out.println("cannot find result by id");
        return null;
    }
}
