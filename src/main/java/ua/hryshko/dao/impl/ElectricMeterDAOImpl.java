package ua.hryshko.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.hryshko.dao.ElectricMeterDAO;
import ua.hryshko.model.ElectricMeter;
import ua.hryshko.model.GasMeter;

import java.util.List;

@Repository
public class ElectricMeterDAOImpl implements ElectricMeterDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public void addElectricMeter(ElectricMeter electricMeter) {
        sessionFactory.getCurrentSession().save(electricMeter);
    }

    @Transactional
    public List<ElectricMeter> listElectricMeters() {
        return sessionFactory.getCurrentSession().createQuery("FROM ElectricMeter").list();
    }

    @Transactional
    public List<ElectricMeter> getElectricMetersByUserId(Long id) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM ElectricMeter where owner_id = :id")
                .setParameter("id",id).list();
    }

    @Transactional
    public ElectricMeter readById(Long id) {
        return sessionFactory.getCurrentSession().get(ElectricMeter.class,id);
    }

    @Transactional
    public void update(ElectricMeter electricMeter) {
        sessionFactory.getCurrentSession().update(electricMeter);

    }

    @Transactional
    public void removeElectricMeter(Long id) {
        ElectricMeter electricMeter =sessionFactory.getCurrentSession().load(ElectricMeter.class,id);
        if(electricMeter!=null){
            sessionFactory.getCurrentSession().delete(electricMeter);
        }

    }
}
