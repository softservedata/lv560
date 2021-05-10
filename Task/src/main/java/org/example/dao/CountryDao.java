package org.example.dao;

import org.example.model.Country;

import java.util.List;

public interface CountryDao {
    Country findById(Integer id);
    Country findByName(String name);
    List<Country> listOfCountries();
}
