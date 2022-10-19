package com.softserve.service.impl;

import com.softserve.dto.PersonDto;
import com.softserve.model.Person;
import com.softserve.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private static List<Person> persons = new ArrayList<>();

    static {
        persons.add(new Person("Bill", "Gates"));
        persons.add(new Person("Steve", "Jobs"));
    }

    public void add(PersonDto personDto) {
        persons.add(new Person(personDto.getFirstName(), personDto.getLastName()));
    }

    public List<PersonDto> getAll() {
        List<PersonDto> result = new ArrayList<>();
        for (Person person : persons) {
            result.add(new PersonDto(person.getFirstName(), person.getLastName()));
        }
        return result;
    }

}
