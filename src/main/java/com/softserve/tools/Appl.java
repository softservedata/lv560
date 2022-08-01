package com.softserve.tools;

import java.util.List;

public class Appl {

    public List<IUser> fromCsv(String filename) {
        return User.createUsers(new CSVReader(filename).getAllCells());
    }

    public List<IUser> fromCsv() {
        return fromCsv("users.csv"); // application.properties
    }

    public List<IUser> fromExcel(String filename) {
        return User.createUsers(new ExcelReader(filename).getAllCells());
    }

    public List<IUser> fromExcel() {
        return fromExcel("users.xlsx");

    }

    public static void main(String[] args) {
        Appl app = new Appl();
        // Read Users from CSV/Excel
        //List<IUser> users = app.fromCsv();
        List<IUser> users = app.fromExcel();
        System.out.println("users = " + users);
    }
}
