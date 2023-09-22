package com.pattern.builder.data;

import com.pattern.builder.Car;
import com.pattern.builder.ICar;
import com.pattern.builder.tools.CSVReader;
import com.pattern.builder.tools.ExcelReader;

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

    public static List<ICar> fromCsv(String filename) {
        return Car.getByLists(new CSVReader(filename).getAllCells());
    }

    public static List<ICar> fromCsv() {
        return fromCsv("cars.csv");
    }

    public static List<ICar> fromExcel(String filename) {
        return Car.getByLists(new ExcelReader(filename).getAllCells());
    }

    public static List<ICar> fromExcel() {
        return fromExcel("cars.xlsx");
    }

}
