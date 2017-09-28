package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;

import com.example.facture.jpa.model.InvoiceItem;
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
public class InvoiceItemDAOImpl implements InvoiceItemDAO {

    private static final String SELECT_A_FROM_INVOICE_ITEM_A = "Select a From InvoiceItem a";
    private static final String SELECT_A_FROM_INVOICE_ITEM_A_WHERE_A_NUMBER_CUST_NUMBER = "Select a From InvoiceItem a where a.number = :custNumber";
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void save(InvoiceItem invoiceItem) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(invoiceItem);
    }


    @Override
    public void delete(InvoiceItem invoiceItem) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(invoiceItem);
    }


    @Override
    public void update(InvoiceItem invoiceItem) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(invoiceItem);
    }


    @Override
    @Cacheable(key="#idInvoiceItem")
    public InvoiceItem getById(Long idInvoiceItem) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(InvoiceItem.class, idInvoiceItem);
    }


    @Override
    @Cacheable
    public List<InvoiceItem> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_INVOICE_ITEM_A, InvoiceItem.class).getResultList();
    }


    @Override
    @Cacheable(key="#number")
    public InvoiceItem getInvoiceItemByNumber(int number) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_INVOICE_ITEM_A_WHERE_A_NUMBER_CUST_NUMBER, InvoiceItem.class)
                .setParameter("custNumber", number).getSingleResult();
    }


    @Override
    @Cacheable(key="#idProduct")
    public List<InvoiceItem> getInvoiceItemByIdProduct(Long idProduct) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from InvoiceItem s where s.product.id = :idprod";
        return session.createQuery(hql, InvoiceItem.class)
                .setParameter("idprod", idProduct)
                .getResultList();
    }


    @Override
    @Cacheable(key="#idInvoice")
    public List<InvoiceItem> getInvoiceItemByIdInvoice(Long idInvoice) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from InvoiceItem s where s.invoice.id = :idinv";
        return session.createQuery(hql, InvoiceItem.class)
                .setParameter("idinv", idInvoice)
                .getResultList();
    }


    @Override
    @Cacheable(key="{#idProduct, #idInvoice}")
    public List<InvoiceItem> getInvoiceItems(long idProduct, long idInvoice) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from InvoiceItem s where s.invoice.id = :idinv and s.product.id = :idprod";
        return session.createQuery(hql, InvoiceItem.class)
                .setParameter("idinv", idInvoice)
                .setParameter("idprod", idProduct)
                .getResultList();
    }


}