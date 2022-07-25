package com.project.soft.dao.impl;

import com.project.soft.dao.QuestionHistoryRepository;
import com.project.soft.dao.basedDao.SimpleSoftRepository;
import com.project.soft.entity.QuestionHistory;
import com.project.soft.entity.Result;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class QuestionHistoryRepositoryImpl extends SimpleSoftRepository<QuestionHistory, Long>
    implements QuestionHistoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public QuestionHistoryRepositoryImpl() {
        super(QuestionHistory.class, Long.class);
    }

    @Transactional
    public List<QuestionHistory> findHistoriesByResult(Result result) {
        String jpql = "select r from Result r where r.id = :id";

        TypedQuery<Result> query = entityManager.createQuery(jpql, Result.class);
        query.setParameter("id", result.getId());

        Result resultFound = query.getSingleResult();

        return resultFound.getQuestionHistories();
    }
}
