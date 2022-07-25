package com.project.soft.dao.impl;

import com.project.soft.dao.ResultRepository;
import com.project.soft.dao.basedDao.SimpleSoftRepository;
import com.project.soft.entity.Result;
import com.project.soft.entity.Test;
import com.project.soft.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class ResultRepositoryImpl extends SimpleSoftRepository<Result, Long>
    implements ResultRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public ResultRepositoryImpl() {
        super(Result.class, Long.class);
    }

    public List<Result> findAllByUserAndTest(User user, Test test) {
        String jpql = "select r from Result r " +
                "where r.user.id = :userId and r.test.id = :testId";

        TypedQuery<Result> query = entityManager.createQuery(jpql, Result.class);
        query.setParameter("userId", user.getId());
        query.setParameter("testId", test.getId());

        return query.getResultList();
    }
}
