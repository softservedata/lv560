package com.pattern.builder.data;

import com.pattern.builder.Car;
import com.pattern.builder.ICar;
import com.pattern.builder.tools.CSVReader;

import java.util.List;

public final class CarRepository {

    private CarRepository() {
    }

    public static ICar getDefault() {
        return getBMWx5BlackPetrol();
    }

    public static ICar getBMWx5BlackPetrol() {
        return Car.builder()
                .setBMWx5()
                .setBlack()
                .setPetrol()
                .setAutomat()
                .setLed()
                .setBlackSalon()
                .setTitanRims()
                .build();
    }

    public static ICar getMercedesWhiteDisel() {
        return Car.builder()
                .setMercedes()
                .setWhite()
                .setDisel()
                .setAutomat()
                .setLed()
                .setBlackSalon()
                .setTitanRims()
                .build();
    }

    /*
    public List<ICar> fromCsv(String filename) {
        return Car.getByLists(new CSVReader(filename).getAllCells());
    }

    public List<ICar> fromCsv() {
        return fromCsv("cars.csv");
    }
    */

}
