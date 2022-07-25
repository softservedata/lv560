package com.project.soft.dao.impl;

import com.project.soft.dao.RoleRepository;
import com.project.soft.dao.basedDao.SimpleSoftRepository;
import com.project.soft.entity.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Repository
public class RoleRepositoryImpl extends SimpleSoftRepository<Role, Long>
    implements RoleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public RoleRepositoryImpl() {
        super(Role.class, Long.class);
    }

    public Role findRoleByName(String name) {

        String jpql = "select r from Role r where r.role = :name";
        TypedQuery<Role> query = entityManager.createQuery(jpql, Role.class);
        query.setParameter("name", name);

        return query.getSingleResult();
    }
}