package ru.gstepanov.hibernate2.dao;

import jakarta.persistence.NoResultException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.gstepanov.hibernate2.entity.Store;

public class StoreDao extends AbstractDao<Store> {
    public StoreDao(SessionFactory sessionFactory) {
        super(Store.class, sessionFactory);
    }

    @Override
    public Store getById(Long id) {
        return this.getCurrentSession().get(Store.class, id.byteValue());
    }

    public Store getByAddress(String storeAddress){

        if (storeAddress.isBlank() || storeAddress.isEmpty()) {
            throw new NoResultException("Store address is null! Customer can not visit Store with null address.");
        }

        try {
            Query<Store> query = this.getCurrentSession().createQuery("from Store s where lower(address.address) = :ADDRESS", Store.class);
            query.setParameter("ADDRESS", storeAddress.toLowerCase());
            return query.getSingleResult();
        } catch (NoResultException e){
            throw new RuntimeException("Can not find Store with such address. The customer can not visit unknown store.");
        }
    }
}
