package com.softserve.tools;

import com.softserve.tools.IUser;

import java.util.ArrayList;
import java.util.List;

enum UserColumns {
    NAME(0),
    LOGIN(1),
    PASSWORD(2),
    ROLE(3),
    EMAIL(4),
    ADDRESS(5);
    //
    private int index;

    private UserColumns(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}


public class User implements IUser {
    private final static String EMAIL_SEPARATOR = "@";
    private final static String EMPTY_STRING = new String();

    private String name;     // null by default
    private String login;
    private String password;
    private String role;
    private String email;   // optional
    private String address; // optional

    public User() {
        this.name = EMPTY_STRING;
        this.login = EMPTY_STRING;
        this.password = EMPTY_STRING;
        this.role = EMPTY_STRING;
        this.email = EMPTY_STRING;
        this.address = EMPTY_STRING;
    }

//    public User(String name, String login, String password, String role, String email, String address) {
//        this.name = name;
//        this.login = login;
//        this.password = password;
//        this.role = role;
//        this.email = email;
//        this.address = address;
//    }

    // Static Factory
    public static IUser createUser(List<String> row) {
        List<String> userData = new ArrayList<>(row);
        for (int i = userData.size(); i < UserColumns.values().length; i++) {
            userData.add(EMPTY_STRING);
        }
        return new User()
                .setName(userData.get(UserColumns.NAME.getIndex()))
                .setLogin(userData.get(UserColumns.LOGIN.getIndex()))
                .setPassword(userData.get(UserColumns.PASSWORD.getIndex()))
                .setRole(userData.get(UserColumns.ROLE.getIndex()))
                .setEmail(userData.get(UserColumns.EMAIL.getIndex()))
                .setAddress(userData.get(UserColumns.ADDRESS.getIndex()));
    }

    public static List<IUser> createUsers(List<List<String>> rows) {
        List<IUser> result = new ArrayList<>();
        //
       String email = rows.get(0).get(UserColumns.EMAIL.getIndex());
        if ((email != null) && (!email.contains(EMAIL_SEPARATOR))) {
            rows.remove(0);
        }
        for (List<String> row : rows) {
            result.add(createUser(row));
        }
        return result;
    }

    // setters

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setRole(String role) {
        this.role = role;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    // getters

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "\n\tUser {" +
                "name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
