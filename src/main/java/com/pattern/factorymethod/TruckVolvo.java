package com.pattern.factorymethod;

import com.pattern.builder.Car;
import com.pattern.builder.ICar;

public class TruckVolvo implements IFactory {

    public ICar buildCar() {
        return Car.builder()
                .setTruck()
                .setRed()
                .setDisel()
                .setAutomat()
                .setXenon()
                .setBlackSalon()
                .setMetalRims()
                .build();
    }
}
