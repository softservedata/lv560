package com.project.soft.dao.impl;

import com.project.soft.dao.QuestionTypeRepository;
import com.project.soft.dao.basedDao.SimpleSoftRepository;
import com.project.soft.entity.QuestionType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Repository
public class QuestionTypeRepositoryImpl extends SimpleSoftRepository<QuestionType, Long>
    implements QuestionTypeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public QuestionTypeRepositoryImpl() {
        super(QuestionType.class, Long.class);
    }

    public QuestionType findTypeByName(String name) {

        String jpql = "select t from QuestionType t where t.questionType = :name";
        TypedQuery<QuestionType> query = entityManager.createQuery(jpql, QuestionType.class);
        query.setParameter("name", name);

        return query.getSingleResult();
    }
}
