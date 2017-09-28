package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@CacheConfig(cacheNames = "application-cache")
public class PriceGroupDAOImpl implements PriceGroupDAO {

    private static final String SELECT_A_FROM_PRICE_GROUP_A = "Select a From PriceGroup a";
    private static final String SELECT_A_FROM_PRICE_GROUP_A_WHERE_A_NAME_LIKE_CUST_NAME = "Select a From PriceGroup a where a.name like :custName";
    private static final String SELECT_A_FROM_PRICE_GROUP_A_WHERE_A_DISCOUNT_CUST_DISCOUNT = "Select a From PriceGroup a where a.discount = :custDiscount";

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void save(PriceGroup priceGroup) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(priceGroup);
    }


    @Override
    public void delete(PriceGroup priceGroup) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(priceGroup);
    }


    @Override
    public void update(PriceGroup priceGroup) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(priceGroup);
    }


    @Override
    @Cacheable(key="#idPriceGroup")
    public PriceGroup getById(Long idPriceGroup) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(PriceGroup.class, idPriceGroup);
    }

    @Override
    @Cacheable
    public List<PriceGroup> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_PRICE_GROUP_A, PriceGroup.class).getResultList();
    }

    @Override
    @Cacheable(key="#name")
    public PriceGroup getPriceGroupByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_PRICE_GROUP_A_WHERE_A_NAME_LIKE_CUST_NAME, PriceGroup.class)
                .setParameter("custName", name).getSingleResult();
    }

    @Override
    @Cacheable(key="#discount")
    public PriceGroup getPriceGroupByDiscount(int discount) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_PRICE_GROUP_A_WHERE_A_DISCOUNT_CUST_DISCOUNT, PriceGroup.class)
                .setParameter("custDiscount", discount).getSingleResult();
    }


    @Override
    public List<Customer> getCustomers(PriceGroup priceGroup) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Customer c JOIN FETCH c.priceGroup p where p.id = :id";
        return session.createQuery(hql, Customer.class)
                .setParameter("id", priceGroup.getId())
                .getResultList();
    }
}
