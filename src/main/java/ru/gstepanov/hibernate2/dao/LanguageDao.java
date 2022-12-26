package ru.gstepanov.hibernate2.dao;

import org.hibernate.SessionFactory;
import ru.gstepanov.hibernate2.entity.Language;

public class LanguageDao extends AbstractDao<Language>{
    public LanguageDao(SessionFactory sessionFactory) {
        super(Language.class, sessionFactory);
    }

    @Override
    public Language getById(Long id) {
        return this.getCurrentSession().get(Language.class, id.byteValue());
    }
}
