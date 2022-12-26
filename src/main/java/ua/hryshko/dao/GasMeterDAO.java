package ua.hryshko.dao;

import ua.hryshko.model.GasMeter;

import java.util.List;

public interface GasMeterDAO {

    void addGasMeter(GasMeter gasMeter);

    List<GasMeter> listGasMeters();
    List<GasMeter> getGasMetersByUserId(Long id);

    GasMeter readById(Long id);

    void update(GasMeter gasMeter);


    void removeGasMeter(Long id);
}
