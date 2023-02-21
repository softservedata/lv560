package com.softserve.edu.data;

import java.util.ArrayList;
import java.util.List;

interface IFirstname {
    ILastname setFirstname(String firstname);
}

interface ILastname {
    ILoginname setLastname(String lastname);
}

interface ILoginname {
    IEmail setLoginname(String loginname);
}

interface IEmail {
    IPassword setEmail(String email);
}

interface IPassword {
    ICountry setPassword(String password);
}

interface ICountry {
    ICity setCountry(String country);
}

interface ICity {
    IPost setCity(String city);
}

interface IPost {
    IAddress setPost(String post);
}

interface IAddress {
    IUserBuid setAddress(String address);
}

interface IUserBuid {
    IUserBuid setPhonenumber(String phonenumber);
    IUserBuid setCompany(String company);
    // 5. Add Builder
    //User build();
    // 6. Add Dependency Inversion
    IUser build();
}

public class User implements IFirstname, ILastname, ILoginname,
        IEmail, IPassword, ICountry, ICity, IPost, IAddress, IUserBuid, IUser {

    public static enum UserColumns {
        FIRST_NAME(0),
        LAST_NAME(1),
        LOGIN_NAME(2),
        EMAIL(3),
        PASSWORD(4),
        PHONE_NUMBER(5),
        COUNTRY(6),
        CITY(7),
        POST_CODE(8),
        ADDRESS(9),
        COMPANY(10);
        //
        private int index;

        private UserColumns(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    private final static String EMAIL_SEPARATOR = "@";
    public final static String EMPTY_STRING = new String();
    private String firstname;
    private String lastname;
    private String loginname;
    private String email;
    private String password;
    private String phonenumber;     // optional
    private String country;
    private String city;
    private String post;
    private String address;
    private String company;         // optional

    /* 1. Classic Constructor
    public User(String firstname, String lastname, String loginname,
                String email, String password, String phonenumber,
                String country, String city, String post,
                String address, String company) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.loginname = loginname;
        this.email = email;
        this.password = password;
        this.phonenumber = phonenumber;
        this.country = country;
        this.city = city;
        this.post = post;
        this.address = address;
        this.company = company;
    }
    */

    // 2. Default Constructor
    //public User() {
    /* 4. Add Static Factory
    private User() {
        firstname = "";
        lastname = "";
        loginname = "";
        email = "";
        password = "";
        phonenumber = ""; // optional
        country = "";
        city = "";
        post = "";
        address = "";
        company = ""; // optional
    }
    */

    // 5. Add Builder
    private User() {
        phonenumber = ""; // optional
        company = "";     // optional
    }

    // 4. Add Static Factory
    //  public static User get() {
    // 5. Add Builder
    public static IFirstname get() {
        return new User();
    }

    // setters

    // 1. Classic Constructor
    //public void setFirstname(String firstname) {
    // 3. Add Fluent Interface
    //public User setFirstname(String firstname) {
    // 5. Add Builder
    public ILastname setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ILoginname setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public IEmail setLoginname(String loginname) {
        this.loginname = loginname;
        return this;
    }

    public IPassword setEmail(String email) {
        this.email = email;
        return this;
    }

    public ICountry setPassword(String password) {
        this.password = password;
        return this;
    }

    public IUserBuid setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
        return this;
    }

    public ICity setCountry(String country) {
        this.country = country;
        return this;
    }

    public IPost setCity(String city) {
        this.city = city;
        return this;
    }

    public IAddress setPost(String post) {
        this.post = post;
        return this;
    }

    public IUserBuid setAddress(String address) {
        this.address = address;
        return this;
    }

    public IUserBuid setCompany(String company) {
        this.company = company;
        return this;
    }

    // 5. Add Builder
    //public User build() {
    // 6. Add Dependency Inversion
    public IUser build() {
        return this;
    }

    // getters

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getLoginname() {
        return loginname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getPost() {
        return post;
    }

    public String getAddress() {
        return address;
    }

    public String getCompany() {
        return company;
    }

    @Override
    public String toString() {
        return "\nUser{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", loginname='" + loginname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", post='" + post + '\'' +
                ", address='" + address + '\'' +
                ", company='" + company + '\'' +
                '}';
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public static IUser getByList(List<String> row) {
        //logger.trace("row.size() = " + row.size() + " UserColumns.values().length = " + UserColumns.values().length);
        List<String> userData = new ArrayList<>(row);
        for (int i = userData.size(); i < UserColumns.values().length; i++) {
            userData.add(EMPTY_STRING);
        }
        return User.get()
                .setFirstname(userData.get(UserColumns.FIRST_NAME.getIndex()))
                .setLastname(userData.get(UserColumns.LAST_NAME.getIndex()))
                .setLoginname(userData.get(UserColumns.LOGIN_NAME.getIndex()))
                .setEmail(userData.get(UserColumns.EMAIL.getIndex()))
                .setPassword(userData.get(UserColumns.PASSWORD.getIndex()))
                .setCountry(userData.get(UserColumns.COUNTRY.getIndex()))
                .setCity(userData.get(UserColumns.CITY.getIndex()))
                .setPost(userData.get(UserColumns.POST_CODE.getIndex()))
                .setAddress(userData.get(UserColumns.ADDRESS.getIndex()))
                .setCompany(userData.get(UserColumns.COMPANY.getIndex()))
                .setPhonenumber(userData.get(UserColumns.PHONE_NUMBER.getIndex()) != null ? userData.get(UserColumns.PHONE_NUMBER.getIndex()) : EMPTY_STRING)
                .build();
    }

    public static List<IUser> getByLists(List<List<String>> rows) {
        List<IUser> result = new ArrayList<>();
        // TODO Verify Test Data as Valid
        if (!rows.get(0).get(UserColumns.EMAIL.getIndex())
                .contains(EMAIL_SEPARATOR)) {
            rows.remove(0);
        }
        for (List<String> currentRow : rows) {
            result.add(getByList(currentRow));
        }
        return result;
    }

}
