package ru.gstepanov.hibernate2.dao;

import jakarta.persistence.NoResultException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.gstepanov.hibernate2.entity.Country;

public class CountryDao extends AbstractDao<Country>{
    public CountryDao(SessionFactory sessionFactory) {
        super(Country.class, sessionFactory);
    }
    public Country getByName(String name){

        if (name.isEmpty() || name.isBlank()){
            throw new NoResultException();
        }

        try {
            Query<Country> query = this.getCurrentSession().createQuery("from Country cntr where lower(cntr.country) = :NAME", Country.class);
            query.setParameter("NAME", name.toLowerCase());
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }
}
