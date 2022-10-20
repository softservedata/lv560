package com.example.CinemaBoot.models;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Gender {
    MALE(1),
    FEMALE(2);

    private int value;

    private Gender(int value){
        this.value = value;
    }

    public static Gender getGender(int id) {
        return Arrays
                .stream(values())
                .filter(gender -> gender.getValue() == id)
                .findFirst()
                .orElse(null);
    }

}
