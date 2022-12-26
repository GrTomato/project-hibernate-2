package ru.gstepanov.hibernate2;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.gstepanov.hibernate2.converter.RatingConverter;
import ru.gstepanov.hibernate2.dao.ActorDao;
import ru.gstepanov.hibernate2.entity.*;

import java.io.Closeable;
import java.io.IOException;

public class MySessionFactory implements Closeable {

    private static SessionFactory sessionFactory;
    private MySessionFactory(){}

    public static SessionFactory getInstance(){
        if (sessionFactory == null){
            Configuration configure = new Configuration()
                    .addAnnotatedClass(Actor.class)
                    .addAnnotatedClass(Address.class)
                    .addAnnotatedClass(Category.class)
                    .addAnnotatedClass(City.class)
                    .addAnnotatedClass(Country.class)
                    .addAnnotatedClass(Customer.class)
                    .addAnnotatedClass(Film.class)
                    .addAnnotatedClass(FilmRating.class)
                    .addAnnotatedClass(Inventory.class)
                    .addAnnotatedClass(Language.class)
                    .addAnnotatedClass(Payment.class)
                    .addAnnotatedClass(Rental.class)
                    .addAnnotatedClass(Staff.class)
                    .addAnnotatedClass(Store.class)
                    .addAnnotatedClass(RatingConverter.class)
                    .configure();
            sessionFactory = configure.buildSessionFactory();
        }
        return sessionFactory;
    }

    @Override
    public void close() throws IOException {
        this.sessionFactory.close();
    }
}
