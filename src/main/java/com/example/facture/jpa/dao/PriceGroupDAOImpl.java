package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import java.util.List;



@Repository
public class PriceGroupDAOImpl implements PriceGroupDAO {

    @Autowired
    private SessionFactory sessionFactory;

    //Saving Product object to database
    @Override
    public void save(PriceGroup priceGroup) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(priceGroup);
    }

    //Deleting Product object from database
    @Override
    public void delete(PriceGroup priceGroup) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(priceGroup);
    }

    //Updating Product object from database
    @Override
    public void update(PriceGroup priceGroup) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(priceGroup);
    }

    //Getting Product object from database by id
    @Override
    @Cacheable("application-cache")
    public PriceGroup getById(Long idPriceGroup) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(PriceGroup.class, idPriceGroup);
    }

    @Override
    @Cacheable("application-cache")
    public List<PriceGroup> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select a From PriceGroup a", PriceGroup.class).getResultList();
    }

    @Override
    @Cacheable("application-cache")
    public PriceGroup getPriceGroupByName(String name){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select a From PriceGroup a where a.name like :custName", PriceGroup.class)
                .setParameter("custName", name).getSingleResult();
    }

    @Override
    @Cacheable("application-cache")
    public PriceGroup getPriceGroupByDiscount(int discount){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select a From PriceGroup a where a.discount = :custDiscount", PriceGroup.class)
                .setParameter("custDiscount", discount).getSingleResult();
    }

    //Getting Customers objects from PriceGroup object from database
    @Override
    public List<Customer> getCustomers(PriceGroup priceGroup) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Customer c JOIN FETCH c.priceGroup p where p.id = :id";
        return session.createQuery(hql, Customer.class)
                .setParameter("id", priceGroup.getId())
                .getResultList();
    }
}