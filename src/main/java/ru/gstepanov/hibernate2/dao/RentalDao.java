package ru.gstepanov.hibernate2.dao;

import jakarta.persistence.NoResultException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.gstepanov.hibernate2.entity.Rental;

public class RentalDao extends AbstractDao<Rental> {

    public RentalDao(SessionFactory sessionFactory) {
        super(Rental.class, sessionFactory);
    }


    public Rental getByCustomerStore(String firstName, String lastName, String filmTitle){

        if (firstName.isEmpty() || firstName.isBlank() || lastName.isBlank() || lastName.isEmpty() || filmTitle.isEmpty() || filmTitle.isBlank()){
            throw new NoResultException("Nothing found for null input data.");
        }

        try {
            String hql = String.join(" ",
                    "from Rental rent",
                    "where lower(customer.firstName) = :FIRSTNAME and lower(customer.lastName) = :LASTNAME",
                    "and rent.returnDate is null",
                    "and inventory.film.title = :FILMTITLE"
            );

            Query<Rental> query = this.getCurrentSession().createQuery(hql, Rental.class);
            query.setParameter("FIRSTNAME", firstName.toLowerCase());
            query.setParameter("LASTNAME", lastName.toLowerCase());
            query.setParameter("FILMTITLE", filmTitle.toLowerCase());
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException();
        }

    }
}
