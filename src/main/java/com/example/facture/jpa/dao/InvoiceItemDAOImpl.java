package com.example.facture.jpa.dao;
import com.example.facture.jpa.model.*;

import com.example.facture.jpa.model.InvoiceItem;
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
public class InvoiceItemDAOImpl implements InvoiceItemDAO {

    @Autowired
    private SessionFactory sessionFactory;

    //Saving InvoiceItem object to database
    @Override
    public void save(InvoiceItem invoiceItem) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(invoiceItem);
    }

    //Deleting InvoiceItem object from database
    @Override
    public void delete(InvoiceItem invoiceItem) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(invoiceItem);
    }

    //Updating InvoiceItem object from database
    @Override
    public void update(InvoiceItem invoiceItem) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(invoiceItem);
    }

    //Getting InvoiceItem object from database by id
    @Override
    @Cacheable("application-cache")
    public InvoiceItem getById(Long idInvoiceItem) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(InvoiceItem.class, idInvoiceItem);
    }

    //Getting all invoiceitems object from database and set to list
    @Override
    @Cacheable("application-cache")
    public List<InvoiceItem> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select a From InvoiceItem a", InvoiceItem.class).getResultList();
    }

    //Getting InvoiceItem object from database by number
    @Override
    @Cacheable("application-cache")
    public InvoiceItem getInvoiceItemByNumber(int number) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select a From InvoiceItem a where a.number = :custNumber", InvoiceItem.class)
                .setParameter("custNumber", number).getSingleResult();
    }



}