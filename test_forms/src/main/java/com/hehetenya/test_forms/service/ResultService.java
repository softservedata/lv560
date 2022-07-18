package com.hehetenya.test_forms.service;

import com.hehetenya.test_forms.dao.impl.DaoFactory;
import com.hehetenya.test_forms.dto.*;
import com.hehetenya.test_forms.entity.Option;
import com.hehetenya.test_forms.entity.Result;
import com.hehetenya.test_forms.exeptions.AppException;
import com.hehetenya.test_forms.exeptions.DBException;

import java.util.ArrayList;
import java.util.List;

public class ResultService {
    public static List<ResultDTO> getAllResults() {
        List<ResultDTO> resultDTOS = new ArrayList<>();
        try {
            List<Result> results = DaoFactory.getResultDao().getAll();
            for (Result r : results) {
                resultDTOS.add(transform(r));
            }
        }catch (DBException e){
            throw new AppException();
        }
        return resultDTOS;
    }

    public static ResultDTO transform(Result result){
        List<OptionDTO> optionDTOS = new ArrayList<>();
        for (Option a:
                result.getAnswers()) {
            optionDTOS.add(AnswerService.transform(a));
        }
        return new ResultDTO(result.getId(),
                UserService.transform(result.getUser()),
                TestService.transform(result.getTest()),
                result.getGrade(),
                optionDTOS);
    }

    public static List<ResultDTO> getAllResultsOfAUser(UserDTO user) {
        List<ResultDTO> resultDTOS = getAllResults();
        resultDTOS.removeIf(r -> !r.getUser().getLogin().equals(user.getLogin()));
        return resultDTOS;
    }

    private static void countGrade(ResultDTO resultDTO) {
        double maxPointsByTest = 0;
        double actualPointByTest = 0;
        for (QuestionDTO q : resultDTO.getTest().getQuestions()) {
            maxPointsByTest += q.getPoints();
            actualPointByTest += countPointsByQuestion(resultDTO, q);
        }
        resultDTO.setGrade((int) (actualPointByTest/maxPointsByTest * 100));
        if (resultDTO.getGrade() < 0) resultDTO.setGrade(0);
    }

    private static double countPointsByQuestion(ResultDTO resultDTO, QuestionDTO q){
        double numberOfChoices = q.getOptions().size();
        double numbOfCorrect = 0;
        for (OptionDTO a : q.getOptions()) {
            if (a.isCorrect() && resultDTO.getAnswers().contains(a)) {
                ++numbOfCorrect;
            } else if (!a.isCorrect() && !resultDTO.getAnswers().contains(a)) {
                ++numbOfCorrect;
            }
        }
        return numbOfCorrect * q.getPoints() / numberOfChoices;
    }

    public static void sendNewResult(ResultDTO resultDTO) {
        countGrade(resultDTO);
        List<Option> answers = new ArrayList<>();
        for(OptionDTO a: resultDTO.getAnswers()){
            answers.add(AnswerService.transformDTO(a));
        }
        try {
            DaoFactory.getResultDao().create(new Result(UserService.transformDTO(resultDTO.getUser()),
                    TestService.transformDTO(resultDTO.getTest()),
                    resultDTO.getGrade(),
                    answers));
        }catch (DBException e){
            throw new AppException();
        }
    }


    public static Result transformDTO(ResultDTO result){
        List<Option> options = new ArrayList<>();
        for (OptionDTO a:
                result.getAnswers()) {
            options.add(AnswerService.transformDTO(a));
        }
        return new Result(UserService.transformDTO(result.getUser()),
                TestService.transformDTO(result.getTest()),
                result.getGrade(),
                options);
    }

    public static ResultDTO getResultById(int resultId) {
        List<ResultDTO> resultDTOS = getAllResults();
        for(ResultDTO r: resultDTOS){
            if(r.getId() == resultId){
                return r;
            }
        }
        return null;
    }
}
