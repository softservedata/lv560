package com.softserve.edu;

import com.softserve.edu.data.IUser;
import com.softserve.edu.data.User;
import com.softserve.edu.data.UserRepo;
import com.softserve.edu.data.UserRepository;

import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("start");
        //
        /* 1. Classic Constructor
        User user = new User("firstname1", "lastname1", "loginname1",
                "email1", "password1", "phonenumber1",
                "country1", "city1", "post1",
                "address1", "company1");
        System.out.println("user = " + user);
        */
        //
        /* 2. Default Constructor
        User user = new User();
        user.setFirstname("firstname2");
        user.setLastname("lastname2");
        user.setLoginname("loginname2");
        user.setEmail("email2");
        user.setPassword("password2");
        user.setPhonenumber("phonenumber2");
        user.setCountry("country2");
        user.setCity("city2");
        user.setPost("post2");
        user.setAddress("address2");
        user.setCompany("company2");
        System.out.println("user = " + user);
        */
        //
        /* 3. Add Fluent Interface
        User user = new User()
            .setFirstname("firstname3")
            .setLastname("lastname3")
            .setLoginname("loginname3")
            .setEmail("email3")
            .setPassword("password3")
            .setPhonenumber("phonenumber3")
            .setCountry("country3")
            .setCity("city3")
            .setPost("post3")
            .setAddress("address3")
            .setCompany("company3");
        System.out.println("user = " + user);
        */
        //
        /* 4. Add Static Factory
        User user = User.get()
                .setFirstname("firstnamea4")
                .setLastname("lastname4")
                .setLoginname("loginname4")
                .setEmail("email4")
                .setPassword("password4")
                .setPhonenumber("phonenumber4")
                .setCountry("country4")
                .setCity("city4")
                .setPost("post34")
                .setAddress("address4")
                .setCompany("company4");
        System.out.println("user = " + user);
        */
        //
        /* 5. Add Builder
        User user = User.get()
                .setFirstname("firstnamea5")
                .setLastname("lastname5")
                .setLoginname("lastname5")
                .setEmail("loginname5")
                .setPassword("password5")
                .setCountry("country5")
                .setCity("city5")
                .setPost("post5")
                .setAddress("address5")
                .setCompany("company5")
                .build();
        System.out.println("email = " + user.setEmail("hahaha")); // may be error
        System.out.println("user = " + user);
        */
        //
        /* 6. Add Dependency Inversion
        IUser user = User.get()
                .setFirstname("firstnamea6")
                .setLastname("lastname6")
                .setLoginname("lastname6")
                .setEmail("loginname6")
                .setPassword("password6")
                .setCountry("country6")
                .setCity("city6")
                .setPost("post6")
                .setAddress("address6")
                .setCompany("company6")
                .setPhonenumber("Phonenumber6")
                .build();
        //System.out.println("email = " + user.setEmail("hahaha")); // Compile Error
        //System.out.println("email = " + ((User) user).setEmail("hahaha")); // Code Smell
        System.out.println("user = " + user);
        */
        //
        /* 7. Add Repository created by Singleton
        IUser user = UserRepository.get().exist();
        System.out.println("user = " + user);
        */
        //
        /* 7a. Add Repository created by static methods
        IUser user = UserRepo.getExist();
        System.out.println("user = " + user);
        */
        //
        // 8. Read data from external files
        //List<IUser> users = UserRepository.get().fromCsv();
        List<IUser> users = UserRepository.get().fromExcel();
        for (IUser current : users) {
            System.out.println("user = " + current);
        }
    }
}
