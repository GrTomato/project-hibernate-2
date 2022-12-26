package ru.gstepanov.hibernate2.service;

import jakarta.persistence.NoResultException;
import ru.gstepanov.hibernate2.dao.CustomerDao;
import ru.gstepanov.hibernate2.entity.Address;
import ru.gstepanov.hibernate2.entity.Customer;
import ru.gstepanov.hibernate2.entity.Store;

public class CustomerService {

    private final CustomerDao customerDao;

    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Customer getInstance(String firstName,
                               String lastName,
                               Store visitedStore,
                               Address customerAddress,
                               String customerEmail
    ){
        try{
            return customerDao.getByName(firstName, lastName);
        } catch (NoResultException e){

            Customer customer = new Customer();
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setActive(true);
            customer.setStore(visitedStore);
            customer.setAddress(customerAddress);
            customer.setEmail(customerEmail);

            return customer;
        }
    }

}
