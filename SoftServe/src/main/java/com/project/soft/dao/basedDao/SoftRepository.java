package com.project.soft.dao.basedDao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface SoftRepository<T, ID> {

    Optional<T> findById(ID id);
    List<T> findAll();
    List<T> findALlByIds(List<Long> ids);
    T save(T object);
    void remove(T object);
    void removeById(ID id);
}
