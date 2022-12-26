package ru.gstepanov.hibernate2.dao;

import org.hibernate.SessionFactory;
import ru.gstepanov.hibernate2.entity.Payment;

public class PaymentDao extends AbstractDao<Payment>{
    public PaymentDao(SessionFactory sessionFactory) {
        super(Payment.class, sessionFactory);
    }
}
