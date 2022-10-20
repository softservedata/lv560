package com.example.CinemaBoot.utils;

import com.example.CinemaBoot.models.Gender;

import javax.persistence.AttributeConverter;

public class GenderConverter implements AttributeConverter<Gender, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Gender gender) {
        return gender == null ? null : gender.getValue();
    }

    @Override
    public Gender convertToEntityAttribute(Integer id) {
        return Gender.getGender(id);
    }

}
