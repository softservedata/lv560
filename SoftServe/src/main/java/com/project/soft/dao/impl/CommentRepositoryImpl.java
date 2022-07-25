package com.project.soft.dao.impl;

import com.project.soft.dao.CommentRepository;
import com.project.soft.dao.basedDao.SimpleSoftRepository;
import com.project.soft.entity.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CommentRepositoryImpl extends SimpleSoftRepository<Comment, Long>
    implements CommentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public CommentRepositoryImpl() {
        super(Comment.class, Long.class);
    }

    public List<Comment> findAllByTestId(Long id) {
        String jpql = "select c from Comment c where c.test.id = :id";

        TypedQuery<Comment> query = entityManager.createQuery(jpql, Comment.class);
        query.setParameter("id", id);

        return query.getResultList();
    }
}
