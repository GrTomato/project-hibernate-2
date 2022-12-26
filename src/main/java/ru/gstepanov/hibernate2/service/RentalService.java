package ru.gstepanov.hibernate2.service;

import jakarta.persistence.NoResultException;
import ru.gstepanov.hibernate2.dao.RentalDao;
import ru.gstepanov.hibernate2.entity.Customer;
import ru.gstepanov.hibernate2.entity.Inventory;
import ru.gstepanov.hibernate2.entity.Rental;
import ru.gstepanov.hibernate2.entity.Staff;

import java.time.LocalDateTime;

public class RentalService {

    private final RentalDao rentalDao;

    public RentalService(RentalDao rentalDao) {
        this.rentalDao = rentalDao;
    }

    public Rental getInstance(Inventory inventory, Customer customer, Staff staff){
        Rental rental = new Rental();
        rental.setInventory(inventory);
        rental.setCustomer(customer);
        rental.setStaff(staff);
        return rental;
    }
}
