package com.pattern.builder.data;

import com.pattern.builder.ICar;
import com.pattern.factorymethod.IFactory;
import com.pattern.factorymethod.PickupGMC;
import com.pattern.factorymethod.TruckScania;
import com.pattern.factorymethod.TruckVolvo;

public enum CarRepositoryFactory {
    DEFAULT_CAR(new PickupGMC()),
    PICKUP_GMC(new PickupGMC()),
    TRUCK_VOLVO(new TruckVolvo()),
    TRUCK_SCANIA(new TruckScania());
    //
    private IFactory factory;

    private CarRepositoryFactory(IFactory factory) {
        this.factory = factory;
    }

    public ICar createCar() {
        return factory.buildCar();
    }

}
