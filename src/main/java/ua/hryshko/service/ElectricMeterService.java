package ua.hryshko.service;

import ua.hryshko.model.ElectricMeter;

import java.util.List;

public interface ElectricMeterService {
    void addElectricMeter(ElectricMeter electricMeter);

    List<ElectricMeter> listElectricMeters();

    List<ElectricMeter> getElectricMetersByUserId(Long id);

    ElectricMeter readById(Long id);

    void update(ElectricMeter electricMeter);

    void removeElectricMeter(Long id);
}
