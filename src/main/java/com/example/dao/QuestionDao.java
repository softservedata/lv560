package com.example.dao;

import com.example.model.Answer;
import com.example.model.Question;

import java.util.List;

public interface QuestionDao {

    /**
     * Finds question by id
     *
     * @param id question id to find
     * @return Question object
     */
    Question findQuestionById(Integer id);

    /**
     * Finds answers to a specific question by question id
     *
     * @param id - question id to get answers
     * @return List of answers to concrete question
     */
    List<Answer> findAnswersByQuestions(Integer id);

    /**
     * Saves specified question object
     *
     * @param question - question to save
     */
    void saveQuestion(Question question);

    /**
     * Saves specified Answer object
     *
     * @param answer - answer to save
     */
    void saveAnswer(Answer answer);

    /**
     * Updates specified question
     *
     *
     * @param question - question to update
     */
    void updateQuestion(Question question);

    /**
     * Deletes specified question
     *
     * @param question - question to delete
     */
    void deleteQuestion(Question question);
}
