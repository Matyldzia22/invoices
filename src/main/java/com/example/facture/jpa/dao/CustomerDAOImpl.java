package com.example.facture.jpa.dao;
import com.example.facture.jpa.model.*;

import com.example.facture.jpa.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    //Saving Customer object to database
    @Override
    public void save(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(customer);
    }

    //Deleting Customer object from database
    @Override
    public void delete(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(customer);
    }

    //Updating Customer object from database
    @Override
    public void update(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(customer);
    }

    //Getting Customer object from database by id
    @Override
    @Cacheable("application-cache")
    public Customer getById(Long idCustomer) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(Customer.class, idCustomer);
    }

    //Getting all customers object from database and set to list
    @Override
    @Cacheable("application-cache")
    public List<Customer> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select a From Customer a", Customer.class).getResultList();
    }

    //Getting Customer object from database by name
    @Override
    @Cacheable("application-cache")
    public Customer getCustomerByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select a From Customer a where a.name like :custName", Customer.class)
                .setParameter("custName", name).getSingleResult();
    }

    //Getting Customer object from database by firstName
    @Override
    @Cacheable("application-cache")
    public Customer getCustomerByFirstName(String firstName) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select a From Customer a where a.firstName like :custFirstName", Customer.class)
                .setParameter("custFirstName", firstName).getSingleResult();
    }

    //Getting Customer object from database by lastName
    @Override
    @Cacheable("application-cache")
    public Customer getCustomerByLastName(String lastName) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select a From Customer a where a.lastName like :custLastName", Customer.class)
                .setParameter("custLastName", lastName).getSingleResult();
    }

    //Getting Customer object from database by nip
    @Override
    @Cacheable("application-cache")
    public Customer getCustomerByNip(String nip) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select a From Customer a where a.nip like :custNip", Customer.class)
                .setParameter("custNip", nip).getSingleResult();
    }

    //Getting Customer object from database by email
    @Override
    @Cacheable("application-cache")
    public Customer getCustomerByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select a From Customer a where a.email like :custEmail", Customer.class)
                .setParameter("custEmail", email).getSingleResult();
    }

    //Getting Invoices objects from Customer object from database
    @Override
    public List<Invoice> getInvoices(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Invoice c JOIN FETCH c.customer p where p.id = :id";
        return session.createQuery(hql, Invoice.class)
                .setParameter("id", customer.getId())
                .getResultList();
    }

    //Getting Addresses objects from Customer object from database
    @Override
    public List<Address> getAddresses(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Address c JOIN FETCH c.customer p where p.id = :id";
        return session.createQuery(hql, Address.class)
                .setParameter("id", customer.getId())
                .getResultList();
    }







}