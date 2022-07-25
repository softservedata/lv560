package com.project.soft.dao.impl;

import com.project.soft.dao.SubscriptionRepository;
import com.project.soft.dao.basedDao.SimpleSoftRepository;
import com.project.soft.entity.Subscription;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class SubscriptionRepositoryImpl extends SimpleSoftRepository<Subscription, Long>
    implements SubscriptionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public SubscriptionRepositoryImpl() {
        super(Subscription.class, Long.class);
    }

    @Transactional
    public List<Subscription> findALlByUserId(Long id){
        String jpql = "select s from Subscription s where s.user.id = :id";

        TypedQuery<Subscription> query = entityManager.createQuery(jpql, Subscription.class);
        query.setParameter("id", id);

        return query.getResultList();
    }
}
