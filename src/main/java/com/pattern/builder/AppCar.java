package com.pattern.builder;

import com.pattern.builder.data.CarRepository;

import java.util.List;

public class AppCar {

    public static void main(String[] args) {
        /* 1. Classic Constructor
        Car car = new Car( "model1",  "color1",  "engine1",  "gear1",  "light1");
        car.setSalon("Salon1");
        car.setRims("disk1");
        System.out.println("car = " + car);
        */
        //
        /* 2. Add Fluent Interface
        Car car = new Car( "model2",  "color2",  "engine2",  "gear2",  "light2")
                .setSalon("Salon2")
                .setRims("disk2");
        System.out.println("car = " + car);
        */
        //
        /* 3. Builder. Inner Classes. Static Factory.
        Car car = Car.builder()
                .setBMWx5()
                .setBlack()
                .setPetrol()
                .setAutomat()
                .setLed()
                .setBlackSalon()
                .setTitanRims()
                .build();
        System.out.println("car.getModel() = " + car.setModel("hahaha")); // Update object
        System.out.println("car = " + car);
        */
        //
        /* 4. Dependency inversion.
        ICar car = Car.builder()
                .setBMWx5()
                .setBlack()
                .setPetrol()
                .setAutomat()
                .setLed()
                .setBlackSalon()
                .setTitanRims()
                .build();
        //System.out.println("car.getModel() = " + car.setModel("hahaha")); // Compile Error
        System.out.println("car.getModel() = " + ((Car) car).setModel("hahaha")); // (Car) - Code Smell
        System.out.println("car = " + car);
        */
        //
        /* 5. Repository.
        ICar car = CarRepository.getBMWx5BlackPetrol();
        System.out.println("car = " + car);
        */
        //
        // 6. Static Factory.
        //List<ICar> cars = CarRepository.fromCsv();
        List<ICar> cars = CarRepository.fromExcel();
        System.out.println("cars = " + cars);
        cars.get(0).setHybridStrategyElectric();
        System.out.println("cars = " + cars);
        //
    }
}
