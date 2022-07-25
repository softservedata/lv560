package com.project.soft.dao.impl;

import com.project.soft.dao.UserRepository;
import com.project.soft.dao.basedDao.SimpleSoftRepository;
import com.project.soft.entity.Test;
import com.project.soft.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


@Repository
public class UserRepositoryImpl extends SimpleSoftRepository<User, Long>
    implements UserRepository {

    public UserRepositoryImpl() {
        super(User.class, Long.class);
    }

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<User> findByUsername(String username) {
        String jpql = "select u from User u where u.username = :username";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter("username", username);

        User user = query.getSingleResult();
        return Optional.ofNullable(user);
    }

    public List<User> findUsersByTest(Test test) {
        String jpql = "select distinct r.user from Result r " +
                "where r.test.id = :id";

        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter("id", test.getId());

        return query.getResultList();
    }
}