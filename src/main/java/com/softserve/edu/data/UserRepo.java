package com.softserve.edu.data;

public final class UserRepo {

    private UserRepo() {
    }

    public static IUser getDefault() {
        return  getExist();
    }

    public static IUser getExist() {
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
}
