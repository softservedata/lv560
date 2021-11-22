package com.softserve.edu.data;

public class AppUser {

    public static void main(String[] args) {
        /*- 1. Use Constructor
        User user = new User("firstname1", "lastname1", "login1", "password1", "email1", "organization1", "address1");
        System.out.println(user);
        */
        /*- 2. Use Setters
        User user = new User();
        user.setFirstname("firstname2");
        user.setLastname("lastname2");
        user.setLogin("login2");
        user.setPassword("password2");
        user.setEmail("email2");
        user.setOrganization("organization2");
        user.setAddress("address2");
        System.out.println(user);
        */
        /*- 3. Add Fluent Interface
        User user = new User()
            .setFirstname("firstname3")
            .setLastname("lastname3")
            .setLogin("login3")
            .setPassword("password3")
            .setEmail("email3")
            .setOrganization("organization3")
            .setAddress("address3");
        System.out.println(user);
        */
        /*- 4. Add Static Factory
        User user = User.get()
                .setFirstname("firstname4")
                .setLastname("lastname4")
                .setLogin("login4")
                .setPassword("password4")
                .setEmail("email4")
                .setOrganization("organization4")
                .setAddress("address4");
            System.out.println(user);
        */
        /* 5. Add Builder
        User user = User.get()
                .setFirstname("firstname5")
                .setLastname("lastname5")
                .setLogin("login5")
                .setPassword("password5")
                .setEmail("email5")
                .setOrganization("organization5")
                .build();
        System.out.println(user.setFirstname(""));
        System.out.println(user);
        */
        /* 6. Add Dependency Inversion
        IUser user = User.get()
                .setFirstname("firstname6")
                .setLastname("lastname6")
                .setLogin("login6")
                .setPassword("password6")
                .setEmail("email6")
                .setOrganization("organization6")
                .build();
        //System.out.println(user.setFirstname("")); // Compile Error
        System.out.println(((User) user).setFirstname("")); // Code Smell
        System.out.println(user);
        */
        // 7.8. Add Repository, Singleton
        IUser user = UserRepository.get().getExistUser();
        System.out.println(user);
    }
}
