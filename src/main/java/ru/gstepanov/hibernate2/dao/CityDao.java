package ru.gstepanov.hibernate2.dao;

import jakarta.persistence.NoResultException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.gstepanov.hibernate2.entity.City;

public class CityDao extends AbstractDao<City> {
    public CityDao(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }

    public City getByName(String name){

        if (name.isEmpty() || name.isBlank()){
            throw new NoResultException();
        }

        try {
            Query<City> query = this.getCurrentSession().createQuery("from City cty where lower(cty.city) = :NAME", City.class);
            query.setParameter("NAME", name.toLowerCase());
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }

}
