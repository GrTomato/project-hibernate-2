package ru.gstepanov.hibernate2.dao;

import jakarta.persistence.NoResultException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.gstepanov.hibernate2.entity.Staff;
import ru.gstepanov.hibernate2.entity.Store;

public class StaffDao extends AbstractDao<Staff>{
    public StaffDao(SessionFactory sessionFactory) {
        super(Staff.class, sessionFactory);
    }

    public Staff getEmployeeByStore(Store store){
        if (store == null){
            throw new NoResultException("No employee for null store was found");
        }

        try{
            String hql = "from Staff s where store.id = :STOREID and s.active = 1";
            Query<Staff> query = this.getCurrentSession().createQuery(hql, Staff.class);
            query.setParameter("STOREID", store.getId());
            query.setMaxResults(1);
            return query.uniqueResult();
        } catch (NoResultException e){
            throw new NoResultException("No Staff was found for shop. Please, check data.");
        }
    }
}
