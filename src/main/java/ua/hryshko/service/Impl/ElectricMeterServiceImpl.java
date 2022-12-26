package ua.hryshko.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hryshko.dao.ElectricMeterDAO;
import ua.hryshko.model.ElectricMeter;
import ua.hryshko.service.ElectricMeterService;

import java.util.List;

@Service
public class ElectricMeterServiceImpl implements ElectricMeterService {


    @Autowired
    ElectricMeterDAO meterDAO;

    @Transactional
    public void addElectricMeter(ElectricMeter electricMeter) {
        meterDAO.addElectricMeter(electricMeter);

    }

    @Transactional
    public List<ElectricMeter> listElectricMeters() {
        return meterDAO.listElectricMeters();
    }

    @Transactional
    public List<ElectricMeter> getElectricMetersByUserId(Long id) {
        return meterDAO.getElectricMetersByUserId(id);
    }

    @Transactional
    public ElectricMeter readById(Long id) {
        return meterDAO.readById(id);
    }

    @Transactional
    public void update(ElectricMeter electricMeter) {

        meterDAO.update(electricMeter);
    }

    @Transactional
    public void removeElectricMeter(Long id) {
        meterDAO.removeElectricMeter(id);

    }
}
