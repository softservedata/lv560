package org.example.dao;

import org.example.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryDao {
    Country findById(Integer id);
    Optional<Country> findByName(String name);
    List<Country> listOfCountries();
}
