package com.onyshkevych.dao.imp;

import com.onyshkevych.dao.ResultDAO;
import com.onyshkevych.model.Result;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
@Repository
public class ResultDAOImp implements ResultDAO {

    private final SessionFactory sessionFactory;


    @Autowired
    public ResultDAOImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveResult(Result result) {
        Session session = sessionFactory.getCurrentSession();
        session.save(result);
    }

    @Override
    public List<Result> getAllResults() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Result", Result.class);

        return query.getResultList();
    }
}
