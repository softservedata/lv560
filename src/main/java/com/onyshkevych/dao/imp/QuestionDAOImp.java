package com.onyshkevych.dao.imp;

import com.onyshkevych.dao.QuestionDAO;
import com.onyshkevych.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class QuestionDAOImp implements QuestionDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public QuestionDAOImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Question> getAllQuestions() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Question", Question.class);

        return query.getResultList();
    }

    @Override
    public Question getQuestionById(Integer ques_id) {
        Session session = sessionFactory.getCurrentSession();
        Question question = session.get(Question.class, ques_id);
        return question;
    }

    @Override
    public void saveQuestion(Question question) {
        Session session = sessionFactory.getCurrentSession();
        session.save(question);
    }

    @Override
    public void saveQuiz(Quiz quiz) {
        Session session = sessionFactory.getCurrentSession();
        session.save(quiz);
    }

    @Override
    public List<Quiz> getAllQuiz() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Quiz", Quiz.class);

        return query.getResultList();
    }

    @Override
    public Quiz getQuizById(Integer idquiz) {
        Session session = sessionFactory.getCurrentSession();
        Quiz quiz = session.get(Quiz.class, idquiz);
        return quiz;
    }


}
