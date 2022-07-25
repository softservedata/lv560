package com.project.soft.dao.impl;

import com.project.soft.dao.TopicRepository;
import com.project.soft.dao.basedDao.SimpleSoftRepository;
import com.project.soft.entity.Topic;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class TopicRepositoryImpl extends SimpleSoftRepository<Topic, Long>
    implements TopicRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public TopicRepositoryImpl() {
        super(Topic.class, Long.class);
    }

    public List<Topic> findALlByTitles(List<String> topicsTitle) {
        String jpql = "select t from Topic t where t.title in :titles";

        TypedQuery<Topic> query = entityManager.createQuery(jpql, Topic.class);
        query.setParameter("titles", topicsTitle);

        return query.getResultList();
    }
}
