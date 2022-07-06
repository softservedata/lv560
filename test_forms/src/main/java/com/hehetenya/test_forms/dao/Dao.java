package com.hehetenya.test_forms.dao;

import com.hehetenya.test_forms.exeptions.DBException;

import java.util.List;

public interface Dao <T>{

    public List<T> getAll();
    public T getById(int id);
    public void update(T t);
    public void delete(T t);
    public void create(T t) throws DBException;
}
