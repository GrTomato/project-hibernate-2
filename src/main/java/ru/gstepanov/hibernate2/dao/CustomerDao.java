package ru.gstepanov.hibernate2.dao;

import jakarta.persistence.NoResultException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.gstepanov.hibernate2.entity.Customer;

public class CustomerDao extends AbstractDao<Customer>{
    public CustomerDao(SessionFactory sessionFactory) {
        super(Customer.class, sessionFactory);
    }

    public Customer getByName(String firstName, String lastName){

        if (firstName.isEmpty() || firstName.isBlank() || lastName.isEmpty() || lastName.isBlank() ){
            throw new NoResultException();
        }

        try {
            Query<Customer> query = this.getCurrentSession().createQuery("from Customer cust where lower(cust.firstName) = :NAME and lower(cust.lastName) = :SURNAME", Customer.class);
            query.setParameter("NAME", firstName.toLowerCase());
            query.setParameter("SURNAME", lastName.toLowerCase());
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }
}
