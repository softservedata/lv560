package com.softserve.edu.data;

public final class UserRepository {
    private static volatile UserRepository instance = null;
    
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
        return getExistUser();
    }
    
    public IUser getExistUser() {
        return User.get()
                .setFirstname("firstname6")
                .setLastname("lastname6")
                .setLogin("login6")
                .setPassword("password6")
                .setEmail("email6")
                .setOrganization("organization6")
                .build();
    }
    
    public IUser getNewUser() {
        return User.get()
                .setFirstname("firstname7")
                .setLastname("lastname7")
                .setLogin("login7")
                .setPassword("password7")
                .setEmail("email7")
                .setOrganization("organizatio7")
                .setAddress("address7")
                .build();
    }
    
    /*-
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
    */
}
