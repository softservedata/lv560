package com.softserve.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.entity.Person;
import com.softserve.edu.repository.PersonRepository;
import com.softserve.edu.service.PersonService;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    // @Autowired
    private PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findByName(String name) {
    	if (name.equals("*")) {
    		return personRepository.findAll();
    	}
        return personRepository.findByName(name);
    }

    public boolean addPerson(Person entity) {
        Person result = null;
        if (entity != null) {
            result = personRepository.saveAndFlush(entity);
        }
        return (entity != null) && entity.equals(result);
    }

}
