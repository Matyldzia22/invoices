package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;

import com.example.facture.jpa.model.Invoice;


import com.github.slugify.Slugify;
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
@CacheConfig(cacheNames = "invoice-cache")
public class InvoiceDAOImpl implements InvoiceDAO {

    private static final String SELECT_A_FROM_INVOICE_A = "Select a From Invoice a ORDER BY a.id";
    private static final String SELECT_A_FROM_INVOICE_A_WHERE_A_NUMBER_LIKE_CUST_NUMBER = "Select a From Invoice a where a.numberr like :custNumber";
    private static final String SELECT_A_FROM_INVOICE_A_WHERE_A_SELLING_DATE_CUST_SELLING_DATE = "Select a From Invoice a where a.sellingDate = :custSellingDate";
    private static final String SELECT_A_FROM_INVOICE_A_WHERE_A_INVOICE_DATE_CUST_INVOICE_DATE = "Select a From Invoice a where a.invoiceDate = :custInvoiceDate";
    private static final String SELECT_A_FROM_INVOICE_A_WHERE_A_CONFIRM_DATE_CUST_CONFIRM_DATE = "Select a From Invoice a where a.confirmDate = :custConfirmDate";
    private static final String FROM_INVOICE_S_WHERE_S_ADDRESS_ID_IDADDR_AND_S_CUSTOMER_ID_IDCUST = "from Invoice s where s.address.id = :idaddr and s.customer.id = :idcust";
    private static final String FROM_INVOICE_S_WHERE_S_ADDRESS_ID_IDADDR = "from Invoice s where s.address.id = :idaddr";
    private static final String FROM_INVOICE_S_WHERE_S_CUSTOMER_ID_IDCUST = "from Invoice s where s.customer.id = :idcust";
    private static final String FROM_INVOICE_ITEM_E_JOIN_FETCH_E_INVOICE_U_WHERE_U_ID_ID = "FROM InvoiceItem e JOIN FETCH e.invoice u where u.id = :id";
    private static final String SELECT_ID_FROM_INVOICE_WHERE_ID_SELECT_MAX_ID_FROM_INVOICE = "SELECT(id) FROM Invoice WHERE id = ( SELECT MAX(id) FROM Invoice)";

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    @CachePut
    public void save(Invoice invoice) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(invoice);
    }


    @Override
    @CacheEvict
    public void delete(Invoice invoice) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(invoice);
    }


    @Override
    @CachePut
    public void update(Invoice invoice) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(invoice);
    }


    @Override
    @Cacheable
    public Invoice getById(Long invoiceId) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(Invoice.class, invoiceId);
    }


    @Override
    @Cacheable
    public List<Invoice> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_INVOICE_A, Invoice.class).getResultList();
    }

    @Override
    @Cacheable
    public long getInId() {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery(SELECT_ID_FROM_INVOICE_WHERE_ID_SELECT_MAX_ID_FROM_INVOICE);
        return (long) q.getSingleResult();
    }

    @Override
    @Cacheable
    public double getSum(long id) {
        Session session = sessionFactory.getCurrentSession();
        Query q =  session.createQuery("SELECT SUM(c.nettoPrice * b.number) AS suma FROM Invoice a " +
                "JOIN InvoiceItem b ON a.id= b.invoice.id " +
                "JOIN Product c ON c.id = b.product.id " +
                "WHERE a.id =:id");
        q.setParameter("id", id);


        return(double) q.getSingleResult();
    }

    @Override
    @Cacheable
    public double getSuma(String numberr) {
        Session session = sessionFactory.getCurrentSession();
        Query q =  session.createQuery("SELECT SUM(c.nettoPrice * b.number) AS suma FROM Invoice a " +
                "JOIN InvoiceItem b ON a.id= b.invoice.id " +
                "JOIN Product c ON c.id = b.product.id " +
                "WHERE a.numberr like:numberr");
        q.setParameter("numberr", numberr);


        return(double) q.getSingleResult();
    }



    @Override
    @Cacheable
    public Invoice getInvoiceByNumber(String number) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_INVOICE_A_WHERE_A_NUMBER_LIKE_CUST_NUMBER, Invoice.class)
                .setParameter("custNumber", number).getSingleResult();
    }


    @Override
    @Cacheable
    public List<Invoice> getInvoiceBySellingDate(Date sellingDate) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_INVOICE_A_WHERE_A_SELLING_DATE_CUST_SELLING_DATE, Invoice.class)
                .setParameter("custSellingDate", sellingDate).getResultList();
    }


    @Override
    @Cacheable
    public List<Invoice> getInvoiceByInvoiceDate(Date invoiceDate) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_INVOICE_A_WHERE_A_INVOICE_DATE_CUST_INVOICE_DATE, Invoice.class)
                .setParameter("custInvoiceDate", invoiceDate).getResultList();
    }


    @Override
    @Cacheable
    public List<Invoice> getInvoiceByConfirmDate(Date confirmDate) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_INVOICE_A_WHERE_A_CONFIRM_DATE_CUST_CONFIRM_DATE, Invoice.class)
                .setParameter("custConfirmDate", confirmDate).getResultList();
    }

    @Override
    @Cacheable
    public List<Invoice> getInvoiceByNumberr(String number) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_INVOICE_A_WHERE_A_NUMBER_LIKE_CUST_NUMBER, Invoice.class).setParameter("custNumber", number).getResultList();
    }

    @Override
    public List<InvoiceItem> getInvoiceItems(Invoice invoice) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(FROM_INVOICE_ITEM_E_JOIN_FETCH_E_INVOICE_U_WHERE_U_ID_ID, InvoiceItem.class)
                .setParameter("id", invoice.getId())
                .getResultList();
    }


    @Override
    @Cacheable
    public List<Invoice> getInvoiceBycustomerId(Long customerId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(FROM_INVOICE_S_WHERE_S_CUSTOMER_ID_IDCUST, Invoice.class)
                .setParameter("idcust", customerId)
                .getResultList();
    }


    @Override
    @Cacheable
    public List<Invoice> getInvoiceByaddressId(Long addressId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(FROM_INVOICE_S_WHERE_S_ADDRESS_ID_IDADDR, Invoice.class)
                .setParameter("idaddr", addressId)
                .getResultList();
    }


    @Override
    @Cacheable
    public List<Invoice> getInvoices(long addressId, long customerId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(FROM_INVOICE_S_WHERE_S_ADDRESS_ID_IDADDR_AND_S_CUSTOMER_ID_IDCUST, Invoice.class)
                .setParameter("idaddr", addressId)
                .setParameter("idcust", customerId)
                .getResultList();
    }


}