package ru.gstepanov.hibernate2.service;

import jakarta.persistence.NoResultException;
import ru.gstepanov.hibernate2.dao.AddressDao;
import ru.gstepanov.hibernate2.entity.Address;
import ru.gstepanov.hibernate2.entity.City;

public class AddressService {

    private final AddressDao addressDao;

    public AddressService(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    public Address getInstance(String name, String district, City city, String postalCode, String phone){

        try{
            return addressDao.getByName(name);
        } catch (NoResultException e){

            Address address = new Address();
            address.setAddress(name);
            address.setCity(city);
            address.setDistrict(district);
            address.setPostalCode(postalCode);
            address.setPhone(phone);

            return address;
        }
    }
}
