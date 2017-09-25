package com.example.facture.jpa.dao;
import com.example.facture.jpa.model.*;

import com.example.facture.jpa.model.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.Date;
import java.util.List;

@Repository
public class AddressDAOImpl implements AddressDAO {

    @Autowired
    private SessionFactory sessionFactory;

    //Saving Address object to database
    @Override
    public void save(Address address) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(address);
    }

    //Deleting Address object from database
    @Override
    public void delete(Address address) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(address);
    }

    //Updating Address object from database
    @Override
    public void update(Address address) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(address);
    }

    //Getting Address from database by id
    @Override
    @Cacheable("application-cache")
    public Address getById(Long idAddress) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(Address.class, idAddress);
    }

    //Getting all addresses object from database and set to list
    @Override
    @Cacheable("application-cache")
    public List<Address> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select a From Address a", Address.class).getResultList();
    }

    //Getting Address object from database by city
    @Override
    @Cacheable("application-cache")
    public List<Address> getAddressByCity(String city) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select a From Address a where a.city like :custCity", Address.class)
                .setParameter("custCity", city).getResultList();
    }

    //Getting Address object from database by postcode
    @Override
    @Cacheable("application-cache")
    public List<Address> getAddressByPostCode(String postCode) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select a From Address a where a.postcode like :custPostCode", Address.class)
                .setParameter("custPostCode", postCode).getResultList();
    }

    //Getting Address object from database by street
    @Override
    @Cacheable("application-cache")
    public List<Address> getAddressByStreet(String street) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select a From Address a where a.street like :custStreet", Address.class)
                .setParameter("custStreet", street).getResultList();
    }

    //Getting Invoices objects from Address object from database
    @Override
    public List<Invoice> getInvoices(Address address) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Invoice c JOIN FETCH c.address p where p.id = :id";
        return session.createQuery(hql, Invoice.class)
                .setParameter("id", address.getId())
                .getResultList();
    }




}