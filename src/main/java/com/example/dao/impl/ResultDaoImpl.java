package com.example.dao.impl;

import com.example.dao.ResultDao;
import com.example.model.Result;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResultDaoImpl implements ResultDao {
    private final SessionFactory sessionFactory;


    @Autowired
    public ResultDaoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Result> findAllResults() {
        Session session = sessionFactory.getCurrentSession();

        List<Result> resultList = session
                .createQuery("FROM Result r")
                .getResultList();
        return resultList;
    }

    @Override
    public List<Result> findAllResultsByUserId(Integer id) {
        Session session = sessionFactory.getCurrentSession();

        List<Result> resultList = session
                .createQuery("FROM Result r WHERE r.user.id = :id")
                .setParameter("id", id)
                .getResultList();
        return resultList;
    }

    @Override
    public List<Result> findResultsByTestId(Integer id) {
        Session session = sessionFactory.getCurrentSession();

        List<Result> resultList = session
                .createQuery("FROM Result r WHERE r.test.id = :id")
                .setParameter("id", id)
                .getResultList();
        return resultList;
    }

    @Override
    public Result findResultByUserAndTestId(Integer userId, Integer testId) {
        Session session = sessionFactory.getCurrentSession();

        Result result = (Result)session
                .createQuery("FROM Result r WHERE r.user.id = :userId AND r.test.id = :testId")
                .setParameter("userId", userId)
                .setParameter("testId", testId)
                .getSingleResult();
        return result;
    }

    @Override
    public void save(Result result) {
        Session session = sessionFactory.getCurrentSession();
        session.save(result);
    }

    @Override
    public void update(Result result) {
        Session session = sessionFactory.getCurrentSession();
        session.update(result);
    }

    @Override
    public void delete(Result result) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(result);
    }
}
