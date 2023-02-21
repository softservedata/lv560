package com.softserve.edu.data;

import com.softserve.edu.tools.CSVReader;
import com.softserve.edu.tools.ExcelReader;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public final class UserRepository {
    private static volatile UserRepository instance = null;
    private static final String TIME_TEMPLATE = "HH_mm_ss_S";

    private UserRepository() {
    }

    public static UserRepository get() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UserRepository();
                }
            }
        }
        return instance;
    }

    public IUser getDefault() {
        return  exist();
    }

    public IUser exist() {
        return User.get()
                .setFirstname("firstnamea7")
                .setLastname("lastname7")
                .setLoginname("lastname7")
                .setEmail("loginname7")
                //.setPassword("password7")
                .setPassword(System.getenv().get("MY_PASSWORD_HAHAHA"))
                .setCountry("country7")
                .setCity("city7")
                .setPost("post7")
                .setAddress("address7")
                .setCompany("company7")
                .setPhonenumber("Phonenumber7")
                .build();
    }

    public IUser admin() {
        return User.get()
                .setFirstname("firstnamea7")
                .setLastname("lastname7")
                .setLoginname("lastname7")
                .setEmail("loginname7")
                //.setPassword("password7")
                .setPassword(System.getenv().get("MY_PASSWORD_HAHAHA"))
                .setCountry("country7")
                .setCity("city7")
                .setPost("post7")
                .setAddress("address7")
                .setCompany("company7")
                .setPhonenumber("Phonenumber7")
                .build();
    }

    public IUser create() {
        String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
        String newEmail = "temp" + currentTime + "@gmail.com";
        return User.get()
                .setFirstname("firstnamea7")
                .setLastname("lastname7")
                .setLoginname("lastname7")
                .setEmail(newEmail)
                //.setPassword("password7")
                .setPassword(System.getenv().get("MY_PASSWORD_HAHAHA"))
                .setCountry("country7")
                .setCity("city7")
                .setPost("post7")
                .setAddress("address7")
                .setCompany("company7")
                .setPhonenumber("Phonenumber7")
                .build();
    }


    public List<IUser> fromCsv(String filename) {
        return User.getByLists(new CSVReader(filename).getAllCells());
    }

    public List<IUser> fromCsv() {
        return fromCsv("users.csv");
    }

    public List<IUser> fromExcel(String filename) {
        return User.getByLists(new ExcelReader(filename).getAllCells());
    }

    public List<IUser> fromExcel() {
        return fromExcel("users.xlsx");
    }
}
