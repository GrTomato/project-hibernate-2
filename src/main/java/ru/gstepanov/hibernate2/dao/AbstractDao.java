package ru.gstepanov.hibernate2.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public abstract class AbstractDao<T> {

    private Class<T> clazz;
    private SessionFactory sessionFactory;

    public AbstractDao(Class<T> clazz, SessionFactory sessionFactory) {
        this.clazz = clazz;
        this.sessionFactory = sessionFactory;
    }

    Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public T getById(Long id){
        return this.getCurrentSession().get(clazz, id);
    }

    public List<T> getItems(int from, int count){
        String hql = "from " + clazz.getName();
        Query<T> query = this.getCurrentSession().createQuery(hql, clazz);
        query.setFirstResult(from);
        query.setMaxResults(count);
        return query.list();
    }

    public List<T> getAll(){
        String hql = "from " + clazz.getName();
        return this.getCurrentSession().createQuery(hql, clazz).list();
    }

    public Long getCount(){
        String hql = "select count(*) from " + clazz.getName();
        return this.getCurrentSession().createQuery(hql, Long.class).getSingleResult();
    }

    public T save (T entity){
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public T update(T entity){
        return (T) getCurrentSession().merge(entity);
    }

    public void delete(T entity){
        getCurrentSession().delete(entity);
    }

    public void deleteById(Long id){
        T entity = getById(id);
        delete(entity);
    }
}
