package com.project.soft.dao.basedDao;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

public abstract class SimpleSoftRepository<T, ID> implements SoftRepository<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;

    private final Class<T> domainClass;
    private final Class<ID> idClass;

    public SimpleSoftRepository(Class<T> domainClass, Class<ID> idClass) {
        this.domainClass = domainClass;
        this.idClass = idClass;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(entityManager.find(domainClass, id));
    }

    @Override
    public List<T> findALlByIds(List<Long> ids) {
        String className = domainClass.getSimpleName();

        String jpql = "select t from " + className + " t " +
                "where t.id in :ids";
        TypedQuery<T> query = entityManager.createQuery(jpql, domainClass);
        query.setParameter("ids", ids);

        return query.getResultList();
    }

    @Override
    public List<T> findAll() {
        String className = domainClass.getSimpleName();

        String jpql = "select t from " + className + " t";
        TypedQuery<T> sqlQuery = entityManager.createQuery(jpql, domainClass);

        return sqlQuery.getResultList();
    }

    @Override
    public T save(T object) {
        if (getObjectId(object) == null) {
            entityManager.persist(object);
            return object;
        } else {
            return entityManager.merge(object);
        }
    }

    @Override
    public void remove(T object) {
        entityManager.remove(object);
    }

    @Override
    public void removeById(ID id) {
        String className = domainClass.getSimpleName();
        String jpql = "delete from " + className + " t where t.id =: id";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", id);

        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public ID getObjectId(T object) {
        for (Field field : domainClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                field.setAccessible(true);
                try {
                    Object value = field.get(object);
                    return (ID) value;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
        return null;
    }
}
