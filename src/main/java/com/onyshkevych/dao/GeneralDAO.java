package com.onyshkevych.dao;

import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public interface GeneralDAO<T, ID> {
    List<T> findAll(Session session) throws SQLException;

    T find(ID id, Session session) throws SQLException;

    void delete(ID id, Session session) throws SQLException;

    void update(T entity, Session session) throws SQLException;

    void create(T entity, Session session) throws SQLException;

}
