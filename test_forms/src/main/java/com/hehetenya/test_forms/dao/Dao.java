package com.hehetenya.test_forms.dao;

import com.hehetenya.test_forms.exeptions.DBException;

import java.util.List;

/**
 * Dao interface defines an abstract API that performs CRUD operations on objects of type T.
 * @param <T>  Entity object
 */

public interface Dao <T>{

     List<T> getAll() throws DBException;
     T getById(int id) throws DBException;
     void update(T t) throws DBException;
     void delete(T t) throws DBException;
     void create(T t) throws DBException;
}
