package ru.gstepanov.hibernate2.dao;

import jakarta.persistence.NoResultException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.gstepanov.hibernate2.entity.Film;

public class FilmDao extends AbstractDao<Film>{
    public FilmDao(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }

    public Film getByName(String filmName){

        if (filmName.isEmpty() || filmName.isBlank()){
            throw new NoResultException("No film with empty title");
        }

        try{
            String hql = "from Film f where lower(f.title) = :FILMNAME";
            Query<Film> query = this.getCurrentSession().createQuery(hql, Film.class);
            query.setParameter("FILMNAME", filmName.toLowerCase());
            return query.uniqueResult();
        } catch (NoResultException e){
            throw new NoResultException("No film with such title was found in db");
        }
    }

}
