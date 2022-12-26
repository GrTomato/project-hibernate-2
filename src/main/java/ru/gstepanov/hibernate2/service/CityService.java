package ru.gstepanov.hibernate2.service;

import jakarta.persistence.NoResultException;
import ru.gstepanov.hibernate2.dao.CityDao;
import ru.gstepanov.hibernate2.entity.City;
import ru.gstepanov.hibernate2.entity.Country;

public class CityService {

    private final CityDao cityDao;

    public CityService(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    public City getInstance(String name, Country country){

        try{
            return cityDao.getByName(name);
        } catch (NoResultException e){
            City city = new City();
            city.setCountry(country);
            city.setCity(name);
            return city;
        }
    }

}
