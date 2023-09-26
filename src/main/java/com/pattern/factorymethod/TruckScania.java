package com.pattern.factorymethod;

import com.pattern.builder.Car;
import com.pattern.builder.ICar;

public class TruckScania implements IFactory {

    public ICar buildCar() {
        return Car.builder()
                .setTruck()
                .setBlack()
                .setDisel()
                .setAutomat()
                .setHalogen()
                .setBlackSalon()
                .setMetalRims()
                .build();
    }
}
