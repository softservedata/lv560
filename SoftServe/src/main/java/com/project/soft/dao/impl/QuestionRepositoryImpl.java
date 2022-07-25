package com.project.soft.dao.impl;

import com.project.soft.dao.QuestionRepository;
import com.project.soft.dao.basedDao.SimpleSoftRepository;
import com.project.soft.entity.Question;
import com.project.soft.entity.Test;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Repository
public class QuestionRepositoryImpl extends SimpleSoftRepository<Question, Long>
    implements QuestionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public QuestionRepositoryImpl() {
        super(Question.class, Long.class);
    }

    @Override
    public List<Question> findQuestionsByTest(Test test) {
        String jpql = "select t from Test t where t.id = :id";
        
        TypedQuery<Test> query = entityManager.createQuery(jpql, Test.class);
        query.setParameter("id", test.getId());

        Test testFound = query.getSingleResult();

        return new ArrayList<>(testFound.getQuestions());
    }

    @Override
    public List<Question> findAllByIds(Long[] questionIds) {
        String jpql = "select q from Question q where q.id in :ids";

        TypedQuery<Question> query = entityManager.createQuery(jpql, Question.class);
        query.setParameter("ids", Arrays.asList(questionIds));

        return query.getResultList();
    }
}
