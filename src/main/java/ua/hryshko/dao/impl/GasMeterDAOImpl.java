package ua.hryshko.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.hryshko.dao.GasMeterDAO;
import ua.hryshko.model.GasMeter;

import java.util.List;
@Repository
public class GasMeterDAOImpl implements GasMeterDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public void addGasMeter(GasMeter gasMeter) {
        sessionFactory.getCurrentSession().save(gasMeter);
    }

    @Transactional
    public List<GasMeter> listGasMeters() {
        return sessionFactory.getCurrentSession().createQuery("from GasMeter").list();
    }

    @Transactional
    public List<GasMeter> getGasMetersByUserId(Long id) {
        return sessionFactory.getCurrentSession().createQuery("from GasMeter where owner_id = :id").setParameter("id",id).list();
    }

    @Transactional
    public GasMeter readById(Long id) {
        return (GasMeter) sessionFactory.getCurrentSession().get(GasMeter.class,id);
    }

    @Transactional
    public void update(GasMeter gasMeter) {
        sessionFactory.getCurrentSession().update(gasMeter);
    }

    @Transactional
    public void removeGasMeter(Long id) {
        GasMeter gasMeter = (GasMeter) sessionFactory.getCurrentSession().load(GasMeter.class,id);
        if(gasMeter!=null){
            sessionFactory.getCurrentSession().delete(gasMeter);
        }

    }
}
