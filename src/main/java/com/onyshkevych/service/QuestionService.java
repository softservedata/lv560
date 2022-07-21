package com.onyshkevych.service;

import com.onyshkevych.model.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

public interface QuestionService {

    @Transactional
    String saveQuestion(Question question);

    @Transactional
    List<Question> getAllQuestions();
    /**
     * Get random 5 questions from db to start a quiz
     * @return   question form with list of five questions
     */
    QuestionForm getQuestions();
    /**
     * Get all questions from db to create a quiz
     * @return        question form with list of all questions
     */
    @Transactional
    QuestionForm getQuestionsForQuiz();
    /**
     * Checks what option is an answer on question
     * @param optionA - option of question
     * @param optionB - option of question
     * @param optionC - option of question
     * @param ans - answer of question
     * @return  answer as one of the options
     */
    String checkAnsOption(String optionA, String optionB, String optionC, String ans);
    /**
     * Creates question from title, options and answer
     */
    @Transactional
    Question createQuestion(String title, String optionA, String optionB, String optionC, String ans);

    /**
     * Checks if user is login and then starts quiz
     * Params name and password - are user's name and pass to check if he's registered
     * Result is here to save user's name
     */
    @Transactional
    String startQuiz(String name, String password, Result result, Model m, boolean submitted);

    /**
     * The same as createQuestion method, but it also returns the main page
     */
    @Transactional
    String createQue(String title, String optionA, String optionB, String optionC, String ans);


    @Transactional
    void saveQuiz(Quiz quiz);

    @Transactional
    List<Quiz> getAllQuiz();

    @Transactional
    Question getQuestionById(Integer ques_id);

    @Transactional
    Quiz getQuizById(Integer idquiz);

    @Transactional
    String getQuestionsPage(Model m);
    /**
     * Creating quiz and saving it in db
     * @param qForm - questions list manager chose to form a quiz
     * @param title - list name
     * @return redirecting page
     */
    @Transactional
    String submitQuiz(QuestionForm qForm, String title);
    /**
     * Getting current quiz from db
     * @param idquiz - quiz id
     * @param name - user's name - it's here to check is user registered or not
     * @return quiz page with current quiz data
     */
    @Transactional
    String getQuiz(Integer idquiz,
                   String name,
                   Model m,
                   Result result,
                   boolean submitted);
    /**
     * returning page with quiz list
     */
    @Transactional
    String seeQuiz(Model model);
}
