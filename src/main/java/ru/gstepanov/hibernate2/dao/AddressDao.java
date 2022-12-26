package ru.gstepanov.hibernate2.dao;

import jakarta.persistence.NoResultException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.gstepanov.hibernate2.entity.Address;

public class AddressDao extends AbstractDao<Address> {
    public AddressDao(SessionFactory sessionFactory) {
        super(Address.class, sessionFactory);
    }

    public Address getByName(String name){

        if (name.isEmpty() || name.isBlank()){
            throw new NoResultException();
        }

        try {
            Query<Address> query = this.getCurrentSession().createQuery("from Address add where lower(add.address) = :NAME", Address.class);
            query.setParameter("NAME", name.toLowerCase());
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }
}
