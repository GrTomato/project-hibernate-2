package ru.gstepanov.hibernate2.service;

import ru.gstepanov.hibernate2.entity.Customer;
import ru.gstepanov.hibernate2.entity.Payment;
import ru.gstepanov.hibernate2.entity.Rental;
import ru.gstepanov.hibernate2.entity.Staff;

import java.math.BigDecimal;

public class PaymentService {

    public Payment getInstance(Customer customer, Staff staff, Rental rental, BigDecimal amount){
        Payment payment = new Payment();
        payment.setCustomer(customer);
        payment.setStaff(staff);
        payment.setRental(rental);
        payment.setAmount(amount);
        return payment;
    }

}
