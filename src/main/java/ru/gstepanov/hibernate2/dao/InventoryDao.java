package ru.gstepanov.hibernate2.dao;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import ru.gstepanov.hibernate2.entity.Inventory;
import ru.gstepanov.hibernate2.entity.Store;

public class InventoryDao extends AbstractDao<Inventory> {
    public InventoryDao(SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }

    public Integer getAvailableInventoryId(String filmName, Store visitedStore){

        if (filmName.isEmpty() || filmName.isBlank() || visitedStore == null){
            throw new NoResultException("No result for null value input");
        }

        try {

            String hql = String.join(" ",
                    "select inventory.id",
                    "from Rental r",
                    "where r.returnDate is not null",
                    "and inventory.film.title = :TITLE",
                    "and inventory.store.id = :STOREID"
            );

            Query<Integer> query = this.getCurrentSession().createQuery(hql, Integer.class);
            query.setParameter("TITLE", filmName);
            query.setParameter("STOREID", visitedStore.getId());
            query.setMaxResults(1);

            return query.uniqueResult();
        } catch (NoResultException e){
            try{
                NativeQuery<Integer> nativeQuery = this.getCurrentSession().createNativeQuery(
                        "select i.inventory_id from inventory i left join rental r on i.inventory_id = r.inventory_id where r.rental_id is null",
                        Integer.class);
                return nativeQuery.uniqueResult();
            } catch (NoResultException se) {
                throw new RuntimeException("Can not find free inventory for combination of store and film.");
            }
        }
    }

    @Override
    public Inventory getById(Long id) {
        return this.getCurrentSession().get(Inventory.class, id.intValue());
    }
}
