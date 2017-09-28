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
public class TypeOfCustomerDAOImpl implements TypeOfCustomerDAO {

    private static final String SELECT_A_FROM_TYPE_OF_CUSTOMER_A = "Select a From TypeOfCustomer a";
    private static final String SELECT_A_FROM_TYPE_OF_CUSTOMER_A_WHERE_A_NAME_LIKE_CUST_NAME = "Select a From TypeOfCustomer a where a.name like :custName";


    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void save(TypeOfCustomer typeOfCustomer) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(typeOfCustomer);
    }


    @Override
    public void delete(TypeOfCustomer typeOfCustomer) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(typeOfCustomer);
    }


    @Override
    public void update(TypeOfCustomer typeOfCustomer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(typeOfCustomer);
    }


    @Override
    @Cacheable(key="#idTypeOfCustomer")
    public TypeOfCustomer getById(Long idTypeOfCustomer) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(TypeOfCustomer.class, idTypeOfCustomer);
    }

    @Override
    @Cacheable
    public List<TypeOfCustomer> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_TYPE_OF_CUSTOMER_A, TypeOfCustomer.class).getResultList();
    }

    @Override
    @Cacheable(key="#name")
    public TypeOfCustomer getTypeOfCustomerByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_TYPE_OF_CUSTOMER_A_WHERE_A_NAME_LIKE_CUST_NAME, TypeOfCustomer.class)
                .setParameter("custName", name).getSingleResult();
    }


    @Override
    public List<Customer> getCustomers(TypeOfCustomer typeOfCustomer) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Customer c JOIN FETCH c.typeOfCustomer p where p.id = :id";
        return session.createQuery(hql, Customer.class)
                .setParameter("id", typeOfCustomer.getId())
                .getResultList();
    }
}
