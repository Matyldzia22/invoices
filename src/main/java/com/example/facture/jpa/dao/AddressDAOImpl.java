package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;

import com.example.facture.jpa.model.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;


import javax.persistence.Query;

import java.util.Date;
import java.util.List;

@Repository
@CacheConfig(cacheNames = "application-cache")
public class AddressDAOImpl implements AddressDAO {

    private static final String SELECT_A_FROM_ADDRESS_A = "Select a From Address a";
    private static final String SELECT_A_FROM_ADDRESS_A_WHERE_A_CITY_LIKE_CUST_CITY = "Select a From Address a where a.city like :custCity";
    private static final String SELECT_A_FROM_ADDRESS_A_WHERE_A_POSTCODE_LIKE_CUST_POST_CODE = "Select a From Address a where a.postcode like :custPostCode";
    private static final String SELECT_A_FROM_ADDRESS_A_WHERE_A_STREET_LIKE_CUST_STREET = "Select a From Address a where a.street like :custStreet";


    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void save(Address address) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(address);
    }


    @Override
    public void delete(Address address) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(address);
    }


    @Override
    public void update(Address address) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(address);
    }


    @Override
    @Cacheable(key="#idAddress")
    public Address getById(Long idAddress) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(Address.class, idAddress);
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
        String hql = "FROM Invoice c JOIN FETCH c.address p where p.id = :id";
        return session.createQuery(hql, Invoice.class)
                .setParameter("id", address.getId())
                .getResultList();
    }


    @Override
    @Cacheable(key="#idCustomer")
    public List<Address> getAddressByIdCustomer(Long idCustomer) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Address s where s.customer.id = :idcust";
        return session.createQuery(hql, Address.class)
                .setParameter("idcust", idCustomer)
                .getResultList();

    }

    @Override
    @Cacheable(key="#idCustomer")
    public List<Address> getAddresses(long idCustomer) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Address s where s.customer.id = :idcust";
        return session.createQuery(hql, Address.class)
                .setParameter("idcust", idCustomer)

                .getResultList();
    }


}