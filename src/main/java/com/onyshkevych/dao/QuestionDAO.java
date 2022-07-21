package com.onyshkevych.dao;

import com.onyshkevych.model.Question;
import com.onyshkevych.model.Quiz;
import com.onyshkevych.model.Result;
import com.onyshkevych.model.User;

import java.util.List;

public interface QuestionDAO {

    /**
     * Getting all questions from db.
     *
     */
    List<Question> getAllQuestions();

    /**
     * Getting question entity by id.
     *
     * @param  ques_id   id of question you need
     * @return         Question entity
     */
    Question getQuestionById(Integer ques_id);
    /**
     * Saving Question in db
     *  @param  question   question entity
     */
    void saveQuestion(Question question);

    /**
     * Saving Quiz in db
     *  @param  quiz   question entity
     */
    void saveQuiz(Quiz quiz);
    /**
     * Getting all quiz from db.
     *
     */
    List<Quiz> getAllQuiz();
    /**
     * Getting quiz entity by id.
     *
     * @param  idquiz   id of quiz you need
     * @return         Quiz entity
     */
    Quiz getQuizById(Integer idquiz);
}
