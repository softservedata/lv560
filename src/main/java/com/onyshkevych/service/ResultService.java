package com.onyshkevych.service;

import com.onyshkevych.model.QuestionForm;
import com.onyshkevych.model.Result;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

public interface ResultService {

    /**
     * Save the result of current quiz and current user
     * @return  result page with user's score
     */
    @Transactional
    String submitResult(QuestionForm qForm, Model m, Result result, boolean submitted);

    @Transactional
    int getResult(QuestionForm qForm);

    /**
     * Show all results of users in descending sort
     * @return  page with results of all users
     */
    @Transactional
    String score(Model m);
    /**
     * Show results of current user
     * @param name - user's name
     * @return  page with results of current user
     */
    @Transactional
    String viewUsersResult(String name, Model model);
}
