package ua.hryshko.service;

import ua.hryshko.model.GasMeter;

import java.util.List;

public interface GasMeterService {
    void addGasMeter(GasMeter gasMeter);

    List<GasMeter> listGasMeters();

    List<GasMeter> getGasMetersByUserId(Long id);

    GasMeter readById(Long id);

    void update(GasMeter gasMeter);

    void removeGasMeter(Long id);

}
