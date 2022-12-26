package ru.gstepanov.hibernate2.dao;

import org.hibernate.SessionFactory;
import ru.gstepanov.hibernate2.entity.FilmText;

public class FilmTextDao extends AbstractDao<FilmText>{
    public FilmTextDao(SessionFactory sessionFactory) {
        super(FilmText.class, sessionFactory);
    }
}
