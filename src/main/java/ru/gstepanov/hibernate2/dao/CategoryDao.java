package ru.gstepanov.hibernate2.dao;

import org.hibernate.SessionFactory;
import ru.gstepanov.hibernate2.entity.Category;

public class CategoryDao extends AbstractDao<Category> {
    public CategoryDao(SessionFactory sessionFactory) {
        super(Category.class, sessionFactory);
    }

    @Override
    public Category getById(Long id) {
        return this.getCurrentSession().get(Category.class, id.byteValue());
    }
}
