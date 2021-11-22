package com.softserve.edu.data;

interface IFirstname {
    ILastname setFirstname(String firstname);
}

interface ILastname {
    ILogin setLastname(String lastname);
}

interface ILogin {
    IPassword setLogin(String login);
}

interface IPassword {
    IUserBuild setPassword(String password);
}

interface IUserBuild {
    IUserBuild setEmail(String email);
    IUserBuild setOrganization(String organization);
    IUserBuild setAddress(String address);
    IUser build();
}

public class User implements IFirstname, ILastname, ILogin, IPassword, IUserBuild, IUser {

    private String firstname;
    private String lastname;
    private String login;
    private String password;
    private String email; // optional
    private String organization; // optional
    private String address; // optional

    /*-
    public User(String firstname, String lastname, String login,
             String password, String email, String organization,
             String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.organization = organization;
        this.address = address;
    }
    */

    //public User() {
    private User() {
        //this.firstname = "";
        //this.lastname = "";
        //this.login = "";
        //this.password = "";
        this.email = "";
        this.organization = "";
        this.address = "";
    }

    // Static Factory

    //public static User get() {
    public static IFirstname get() {
        return new User();
    }

    // setters

    //public void setFirstname(String firstname) {
    //public User setFirstname(String firstname) {
    public ILastname setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ILogin setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public IPassword setLogin(String login) {
        this.login = login;
        return this;
    }

    public IUserBuild setPassword(String password) {
        this.password = password;
        return this;
    }

    public IUserBuild setEmail(String email) {
        this.email = email;
        return this;
    }

    public IUserBuild setOrganization(String organization) {
        this.organization = organization;
        return this;
    }

    public IUserBuild setAddress(String address) {
        this.address = address;
        return this;
    }

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

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getOrganization() {
        return organization;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "User [firstname=" + firstname + ", lastname=" + lastname + ", login=" + login + ", password=" + password
                + ", email=" + email + ", organization=" + organization + ", address=" + address + "]";
    }

}
