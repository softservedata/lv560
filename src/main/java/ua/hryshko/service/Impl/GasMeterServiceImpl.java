package ua.hryshko.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hryshko.dao.GasMeterDAO;
import ua.hryshko.model.GasMeter;
import ua.hryshko.service.GasMeterService;

import java.util.List;
@Service
public class GasMeterServiceImpl implements GasMeterService {
    @Autowired
    GasMeterDAO gasMeterDAO;

    @Transactional
    public void addGasMeter(GasMeter gasMeter) {
        gasMeterDAO.addGasMeter(gasMeter);
    }

    @Transactional
    public List<GasMeter> listGasMeters() {
        return gasMeterDAO.listGasMeters();
    }

    @Transactional
    public List<GasMeter> getGasMetersByUserId(Long id) {
        return gasMeterDAO.getGasMetersByUserId(id);
    }

    @Transactional
    public GasMeter readById(Long id) {
        return gasMeterDAO.readById(id);
    }

    @Transactional
    public void update(GasMeter gasMeter) {
        gasMeterDAO.update(gasMeter);
    }

    @Transactional
    public void removeGasMeter(Long id) {
        gasMeterDAO.removeGasMeter(id);
    }

}
