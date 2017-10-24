package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@CacheConfig(cacheNames = "typeOfCustomer-cache")
public class TypeOfCustomerDAOImpl implements TypeOfCustomerDAO {

    private static final String SELECT_A_FROM_TYPE_OF_CUSTOMER_A = "Select a From TypeOfCustomer a";
    private static final String SELECT_A_FROM_TYPE_OF_CUSTOMER_A_WHERE_A_NAME_LIKE_CUST_NAME = "Select a From TypeOfCustomer a where a.name like :custName";
    private static final String FROM_CUSTOMER_C_JOIN_FETCH_C_TYPE_OF_CUSTOMER_P_WHERE_P_ID_ID = "FROM Customer c JOIN FETCH c.typeOfCustomer p where p.id = :id";



    private SessionFactory sessionFactory;

    public TypeOfCustomerDAOImpl (SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    @CachePut
    public void save(TypeOfCustomer typeOfCustomer) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(typeOfCustomer);
    }


    @Override
    @CacheEvict
    public void delete(TypeOfCustomer typeOfCustomer) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(typeOfCustomer);
    }


    @Override
    @CachePut
    public void update(TypeOfCustomer typeOfCustomer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(typeOfCustomer);
    }


    @Override
    @Cacheable
    public TypeOfCustomer getById(Long typeOfCustomerId) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(TypeOfCustomer.class, typeOfCustomerId);
    }

    @Override
    @Cacheable
    public List<TypeOfCustomer> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_TYPE_OF_CUSTOMER_A, TypeOfCustomer.class).getResultList();
    }

    @Override
    @Cacheable
    public TypeOfCustomer getTypeOfCustomerByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_TYPE_OF_CUSTOMER_A_WHERE_A_NAME_LIKE_CUST_NAME, TypeOfCustomer.class)
                .setParameter("custName", name).getSingleResult();
    }


    @Override
    public List<Customer> getCustomers(TypeOfCustomer typeOfCustomer) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(FROM_CUSTOMER_C_JOIN_FETCH_C_TYPE_OF_CUSTOMER_P_WHERE_P_ID_ID, Customer.class)
                .setParameter("id", typeOfCustomer.getId())
                .getResultList();
    }
}
