package com.softserve.inner;

public class AppCar {

    public static void main(String[] args) {
        /* 1 Classic Constructor
        Car car = new Car("bmw", "black", "petrol", "manual", "xenon");
        System.out.println("Car = " + car);
        */
        //
        /* 2. Using setters
        Car car = new Car();
        car.setModel("bmw");
        car.setColor("black");
        car.setEngine("petrol");
        //car.setGear("manual");
        car.setLight("xenon");
        System.out.println("Car = " + car);
        */
        /* 3. Add Fluent Interface
        Car car = new Car()
                .setModel("bmw")
                .setColor("black")
                //.setEngine("petrol")
                .setGear("manual")
                .setLight("manual");
        System.out.println("Car = " + car);
        */
        /* 4. Add Static Factory
        Car car = Car.builder()
                .setModel("bmw")
                .setColor("black")
                .setEngine("petrol")
                .setGear("manual")
                .setLight("manual");
        System.out.println("Car = " + car);
        */
        // Using builder
        Car car = Car.builder()
                .setMercedes()
                .setBlack()
                .setPetrol()
                .setAutomat()
                .setLed()
                .setBlackSalon()
                .build();
        System.out.println("Car = " + car);
    }
}
