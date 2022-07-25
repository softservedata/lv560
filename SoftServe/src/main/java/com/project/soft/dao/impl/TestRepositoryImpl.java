package com.project.soft.dao.impl;

import com.project.soft.dao.TestRepository;
import com.project.soft.dao.basedDao.SimpleSoftRepository;
import com.project.soft.entity.Answer;
import com.project.soft.entity.Question;
import com.project.soft.entity.Test;
import com.project.soft.entity.Topic;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;


@Repository
public class TestRepositoryImpl extends SimpleSoftRepository<Test, Long>
    implements TestRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public TestRepositoryImpl() {
        super(Test.class, Long.class);
    }

    @Override
    public List<Test> findUnarchivedTests() {
        String jpql = "select t from Test t where t.archived = false";
        TypedQuery<Test> query = entityManager.createQuery(jpql, Test.class);

        return query.getResultList();
    }

    @Override
    public List<Test> findArchivedTests() {
        String jpql = "select t from Test t where t.archived = true";
        TypedQuery<Test> query = entityManager.createQuery(jpql, Test.class);

        return query.getResultList();
    }

    @Override
    public Set<Test> findTestsByTopic(Long topicId) {
        String jpql = "select t from Topic t where t.id = :id";
        TypedQuery<Topic> query = entityManager.createQuery(jpql, Topic.class);
        query.setParameter("id", topicId);

        Topic topic = query.getSingleResult();

        return topic.getTests();
    }

    @Override
    public Test saveTest(Test test) {
        entityManager.persist(test);

        for (Question question : test.getQuestions()) {
            if (question.getId() == null) {
                entityManager.persist(question);
            } else {
                entityManager.merge(question);
            }
        }


        return test;
    }
}
