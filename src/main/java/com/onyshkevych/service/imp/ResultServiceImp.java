package com.onyshkevych.service.imp;

import com.onyshkevych.dao.ResultDAO;
import com.onyshkevych.model.Question;
import com.onyshkevych.model.QuestionForm;
import com.onyshkevych.model.Result;
import com.onyshkevych.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class ResultServiceImp implements ResultService {
    private ResultDAO rRepo;
    @Autowired
    public ResultServiceImp(ResultDAO rRepo) {
        this.rRepo = rRepo;
    }
    @Transactional
    @Override
    public String submitResult(QuestionForm qForm, Model m, Result result, boolean submitted) {
        if (!submitted) {
            result.setTotal_correct(getResult(qForm));
            saveScore(result);
            submitted = true;
        }
        return "result";
    }
    public void saveScore(Result result) {
        Result saveResult = new Result();
        saveResult.setUsername(result.getUsername());
        saveResult.setTotal_correct(result.getTotal_correct());
        rRepo.saveResult(saveResult);
    }
    @Transactional
    @Override
    public int getResult(QuestionForm qForm) {
        int correct = 0;
        for(Question q: qForm.getQuestions())
            if (Objects.equals(q.getAns(), q.getChose())) {
                correct++;
            }

        return correct;
    }
    public List<Result> getAllResults(){
        return rRepo.getAllResults();
    }
    public List<Result> getTopScore() {
        List<Result> sList = rRepo.getAllResults();

        sList.sort(new Comparator<Result>() {
            @Override
            public int compare(Result lhs, Result rhs) {
                return Integer.compare(rhs.getTotal_correct(), lhs.getTotal_correct());
            }
        });
        return sList;
    }
    @Transactional
    @Override
    public String score(Model m) {
        List<Result> sList = getTopScore();
        m.addAttribute("sList", sList);
        return "scoreboard";
    }

    @Transactional
    @Override
    public String viewUsersResult(String name, Model model) {
        List<Result> results = getAllResults();
        List<Result> usefulRes = new ArrayList<>();
        for (Result result : results) {
            if ((Objects.equals(result.getUsername(), name))){
                usefulRes.add(result);
            }
        }
        System.out.println(usefulRes);
        model.addAttribute("resultuser", usefulRes);
        return "userresult";
    }
}
