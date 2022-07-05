package com.example.dao.impl;

import com.example.dao.QuestionDao;
import com.example.model.Answer;
import com.example.model.Question;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Repository
public class QuestionDaoImpl implements QuestionDao {

    private final Logger LOGGER = LoggerFactory.getLogger(QuestionDaoImpl.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public QuestionDaoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Question findQuestionById(Integer id) {
        Session session = sessionFactory.getCurrentSession();

        Question question = (Question)session.createQuery("FROM Question q WHERE q.id = :id")
                .setParameter("id", id).getSingleResult();

        LOGGER.info("Question with id:{} found", id);
        return question;
    }

    @Override
    public List<Answer> findAnswersByQuestions(Integer id) {
        Session session = sessionFactory.getCurrentSession();

        List<Answer> result = session
                .createQuery("FROM Answer a WHERE A.id = :id")
                .setParameter("id", id)
                .getResultList();

        LOGGER.info("Questions for test with id:{} found", id);
        return result;
    }

    @Override
    public void updateQuestion(Question question) {
        Session session = sessionFactory.getCurrentSession();
        session.update(question);

        LOGGER.info("Question with id:{} updated", question.getId());
    }

    @Override
    public void deleteQuestion(Question question) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(question);

        LOGGER.info("Question with id:{} deleted", question.getId());
    }

    @Override
    public void saveQuestion(Question question) {
        Session session = sessionFactory.getCurrentSession();
        session.save(question);

        LOGGER.info("Question with id:{} saved", question.getId());
    }

    @Override
    public void saveAnswer(Answer answer) {
        Session session = sessionFactory.getCurrentSession();
        session.save(answer);

        LOGGER.info("Answer with id:{} saved", answer.getId());
    }
}
