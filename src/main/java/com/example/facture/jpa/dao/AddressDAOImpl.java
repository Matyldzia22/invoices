package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;

import com.example.facture.jpa.model.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;


import javax.persistence.Query;

import java.util.Date;
import java.util.List;

@Repository
@CacheConfig(cacheNames = "address-cache")
public class AddressDAOImpl implements AddressDAO {

    private static final String SELECT_A_FROM_ADDRESS_A = "Select a From Address a";
    private static final String SELECT_A_FROM_ADDRESS_A_WHERE_A_CITY_LIKE_CUST_CITY = "Select a From Address a where a.city like :custCity";
    private static final String SELECT_A_FROM_ADDRESS_A_WHERE_A_POSTCODE_LIKE_CUST_POST_CODE = "Select a From Address a where a.postCode like :custPostCode";
    private static final String SELECT_A_FROM_ADDRESS_A_WHERE_A_STREET_LIKE_CUST_STREET = "Select a From Address a where a.street like :custStreet";
    private static final String FROM_ADDRESS_S_WHERE_S_CUSTOMER_ID_IDCUST = "from Address s where s.customer.id = :idcust";
    private static final String FROM_INVOICE_C_JOIN_FETCH_C_ADDRESS_P_WHERE_P_ID_ID = "FROM Invoice c JOIN FETCH c.address p where p.id = :id";


    @Autowired
    private SessionFactory sessionFactory;


    @Override
    @CachePut
    public void save(Address address) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(address);
    }


    @Override
    @CacheEvict
    public void delete(Address address) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(address);
    }


    @Override
    @CachePut
    public void update(Address address) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(address);
    }


    @Override
    @Cacheable
    public Address getById(Long addressId) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(Address.class, addressId);
    }


    @Override
    @Cacheable
    public List<Address> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_ADDRESS_A, Address.class).getResultList();
    }


    @Override
    @Cacheable
    public List<Address> getAddressByCity(String city) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_ADDRESS_A_WHERE_A_CITY_LIKE_CUST_CITY, Address.class)
                .setParameter("custCity", city).getResultList();
    }


    @Override
    @Cacheable
    public List<Address> getAddressByPostCode(String postCode) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_ADDRESS_A_WHERE_A_POSTCODE_LIKE_CUST_POST_CODE, Address.class)
                .setParameter("custPostCode", postCode).getResultList();
    }


    @Override
    @Cacheable
    public List<Address> getAddressByStreet(String street) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_ADDRESS_A_WHERE_A_STREET_LIKE_CUST_STREET, Address.class)
                .setParameter("custStreet", street).getResultList();
    }


    @Override
    public List<Invoice> getInvoices(Address address) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(FROM_INVOICE_C_JOIN_FETCH_C_ADDRESS_P_WHERE_P_ID_ID, Invoice.class)
                .setParameter("id", address.getId())
                .getResultList();
    }


    @Override
    @Cacheable
    public List<Address> getAddresses(long customerId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(FROM_ADDRESS_S_WHERE_S_CUSTOMER_ID_IDCUST, Address.class)
                .setParameter("idcust", customerId)

                .getResultList();
    }


}