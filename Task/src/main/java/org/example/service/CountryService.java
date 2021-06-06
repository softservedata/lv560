package org.example.service;

import org.example.dao.CountryDao;
import org.example.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private final CountryDao countryDao;

    @Autowired
    CountryService(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public List<Country> listOfCountries() {
        return countryDao.listOfCountries();
    }

    public Country findById(Integer id) {
        return countryDao.findById(id);
    }

    public Optional<Country> findByName(String name) {
        return countryDao.findByName(name);
    }
}
