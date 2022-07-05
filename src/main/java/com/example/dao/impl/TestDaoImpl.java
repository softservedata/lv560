package com.example.dao.impl;

import com.example.dao.TestDao;
import com.example.model.Question;
import com.example.model.Test;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestDaoImpl implements TestDao {

    private final SessionFactory sessionFactory;


    @Autowired
    public TestDaoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Test findById(Integer id) {

        Session session = sessionFactory.getCurrentSession();

        Test test = (Test) session
                .createQuery("FROM Test t WHERE t.id = :id")
                .setParameter("id", id)
                .getSingleResult();

        return test;
    }

    @Override
    public List<Test> getAllTests() {
        Session session = sessionFactory.getCurrentSession();

        List<Test> result = session
                .createQuery("FROM Test t")
                .getResultList();

        return result;
    }

    @Override
    public void save(Test test) {
        Session session = sessionFactory.getCurrentSession();

        session.save(test);

    }

    @Override
    public List<Question> getQuestionsByTest(Integer id) {
        Session session = sessionFactory.getCurrentSession();

        List<Question> result = session
                .createQuery("FROM Question q WHERE test.id = :id")
                .setParameter("id", id)
                .getResultList();

        return result;
    }

    @Override
    public void update(Test test) {
        Session session = sessionFactory.getCurrentSession();
        session.update(test);
    }

    @Override
    public void delete(Test test) {
        Session session = sessionFactory.getCurrentSession();
        session.update(test);
    }
}
