package ru.gstepanov.hibernate2.service;

import jakarta.persistence.NoResultException;
import ru.gstepanov.hibernate2.dao.CountryDao;
import ru.gstepanov.hibernate2.entity.Country;

public class CountryService {

    private final CountryDao countryDao;


    public CountryService(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public Country getInstance(String name){

        try{
            return countryDao.getByName(name);
        } catch (NoResultException e){
            Country country = new Country();
            country.setCountry(name);
            return country;
        }
    }

}
