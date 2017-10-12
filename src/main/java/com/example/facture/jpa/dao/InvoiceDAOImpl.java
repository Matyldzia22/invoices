package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;

import com.example.facture.jpa.model.Invoice;
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
public class InvoiceDAOImpl implements InvoiceDAO {

    private static final String SELECT_A_FROM_INVOICE_A = "Select a From Invoice a";
    private static final String SELECT_A_FROM_INVOICE_A_WHERE_A_NUMBER_LIKE_CUST_NUMBER = "Select a From Invoice a where a.number like :custNumber";
    private static final String SELECT_A_FROM_INVOICE_A_WHERE_A_SELLING_DATE_CUST_SELLING_DATE = "Select a From Invoice a where a.sellingDate = :custSellingDate";
    private static final String SELECT_A_FROM_INVOICE_A_WHERE_A_INVOICE_DATE_CUST_INVOICE_DATE = "Select a From Invoice a where a.invoiceDate = :custInvoiceDate";
    private static final String SELECT_A_FROM_INVOICE_A_WHERE_A_CONFIRM_DATE_CUST_CONFIRM_DATE = "Select a From Invoice a where a.confirmDate = :custConfirmDate";
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void save(Invoice invoice) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(invoice);
    }


    @Override
    public void delete(Invoice invoice) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(invoice);
    }


    @Override
    public void update(Invoice invoice) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(invoice);
    }


    @Override
    @Cacheable(key="#idInvoice")
    public Invoice getById(Long idInvoice) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(Invoice.class, idInvoice);
    }


    @Override
    @Cacheable
    public List<Invoice> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_INVOICE_A, Invoice.class).getResultList();
    }


    @Override
    @Cacheable(key="#number")
    public Invoice getInvoiceByNumber(String number) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_INVOICE_A_WHERE_A_NUMBER_LIKE_CUST_NUMBER, Invoice.class)
                .setParameter("custNumber", number).getSingleResult();
    }


    @Override
    @Cacheable(key="#sellingDate")
    public List<Invoice> getInvoiceBySellingDate(Date sellingDate) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_INVOICE_A_WHERE_A_SELLING_DATE_CUST_SELLING_DATE, Invoice.class)
                .setParameter("custSellingDate", sellingDate).getResultList();
    }


    @Override
    @Cacheable(key="#invoiceDate")
    public List<Invoice> getInvoiceByInvoiceDate(Date invoiceDate) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_INVOICE_A_WHERE_A_INVOICE_DATE_CUST_INVOICE_DATE, Invoice.class)
                .setParameter("custInvoiceDate", invoiceDate).getResultList();
    }


    @Override
    @Cacheable(key="#confirmDate")
    public List<Invoice> getInvoiceByConfirmDate(Date confirmDate) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_INVOICE_A_WHERE_A_CONFIRM_DATE_CUST_CONFIRM_DATE, Invoice.class)
                .setParameter("custConfirmDate", confirmDate).getResultList();
    }


    @Override
    public List<InvoiceItem> getInvoiceItems(Invoice invoice) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM InvoiceItem c JOIN FETCH c.product p where p.id = :id";

        return session.createQuery(hql, InvoiceItem.class)
                .setParameter("id", invoice.getId())
                .getResultList();
    }


    @Override
    @Cacheable(key="#idCustomer")
    public List<Invoice> getInvoiceByIdCustomer(Long idCustomer) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Invoice s where s.customer.id = :idcust";
        return session.createQuery(hql, Invoice.class)
                .setParameter("idcust", idCustomer)
                .getResultList();
    }


    @Override
    @Cacheable(key="#idAddress")
    public List<Invoice> getInvoiceByIdAddress(Long idAddress) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Invoice s where s.address.id = :idaddr";
        return session.createQuery(hql, Invoice.class)
                .setParameter("idaddr", idAddress)
                .getResultList();
    }


    @Override
    @Cacheable(key="{#idAddress, #idCustomer}")
    public List<Invoice> getInvoices(long idAddress, long idCustomer) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Invoice s where s.address.id = :idaddr and s.customer.id = :idcust";
        return session.createQuery(hql, Invoice.class)
                .setParameter("idaddr", idAddress)
                .setParameter("idcust", idCustomer)
                .getResultList();
    }


}