package ru.gstepanov.hibernate2.dao;

import org.hibernate.SessionFactory;
import ru.gstepanov.hibernate2.entity.Actor;

public class ActorDao extends AbstractDao<Actor>{
    public ActorDao(SessionFactory sessionFactory) {
        super(Actor.class, sessionFactory);
    }

    @Override
    public Actor getById(Long id) {
        return this.getCurrentSession().get(Actor.class,  id.shortValue());
    }
}
