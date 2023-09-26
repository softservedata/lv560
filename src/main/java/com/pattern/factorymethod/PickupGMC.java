package com.pattern.factorymethod;

import com.pattern.builder.Car;
import com.pattern.builder.ICar;

public class PickupGMC implements IFactory {

    public ICar buildCar() {
        return Car.builder()
                .setPickup()
                .setWhite()
                .setPetrol()
                .setAutomat()
                .setHalogen()
                .setBlackSalon()
                .setMetalRims()
                .build();
    }
}