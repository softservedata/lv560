package ua.hryshko.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.hryshko.dao.HistoryDAO;
import ua.hryshko.model.History;

import java.util.List;

@Repository
public class HistoryDAOImpl implements HistoryDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public void addHistory(History history) {
        sessionFactory.getCurrentSession().save(history);
    }
    @Transactional
    public List<History> findByOwner(Long id) {
        return sessionFactory.getCurrentSession().createQuery("from History where meter_id = :id").setParameter("id",id).list();
    }


    @Transactional
    public void removeHistory(Long id) {
        History history = (History) sessionFactory.getCurrentSession().get(History.class,id);
        if(history!=null){
            sessionFactory.getCurrentSession().delete(history);
        }

    }
}
