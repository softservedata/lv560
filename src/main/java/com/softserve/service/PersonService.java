package com.softserve.service;

import com.softserve.dto.PersonDto;

import java.util.List;

public interface PersonService {

    void add(PersonDto personDto);

    List<PersonDto> getAll();
}
