package com.hehetenya.test_forms.entity;

public enum Role {
    USER(1, "User"), MANAGER(2, "Manager");

    private final int id;
    private final String name;

    Role(int id, String name){
        this.id = id;
        this.name = name;
    }

    public static Role getRole(int id){
        for(Role r: values()){
            if(r.id == id){
                return r;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
