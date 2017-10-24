package com.example.facture.jpa.dao;

import com.example.facture.jpa.dto.InvoiceItemDTO;
import com.example.facture.jpa.model.*;

import com.example.facture.jpa.model.InvoiceItem;
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
@CacheConfig(cacheNames = "invoiceItem-cache")
public class InvoiceItemDAOImpl implements InvoiceItemDAO {

    private static final String FROM_INVOICE_ITEM_S_WHERE_S_PRODUCT_ID_IDPROD = "from InvoiceItem s where s.product.id = :idprod";
    private static final String SELECT_A_FROM_INVOICE_ITEM_A = "Select a From InvoiceItem a";
    private static final String SELECT_A_FROM_INVOICE_ITEM_A_WHERE_A_NUMBER_CUST_NUMBER = "Select a From InvoiceItem a where a.number = :custNumber";
    private static final String FROM_INVOICE_ITEM_S_WHERE_S_INVOICE_ID_IDINV_AND_S_PRODUCT_ID_IDPROD = "from InvoiceItem s where s.invoice.id = :idinv and s.product.id = :idprod";
    private static final String FROM_INVOICE_ITEM_S_WHERE_S_INVOICE_ID_IDINV = "from InvoiceItem s where s.invoice.id = :idinv";
    private static final String SELECT_ID_FROM_INVOICE_ITEM_WHERE_ID_SELECT_MAX_ID_FROM_INVOICE_ITEM = "SELECT(id) FROM InvoiceItem WHERE id = ( SELECT MAX(id) FROM InvoiceItem)";

    private SessionFactory sessionFactory;

    public InvoiceItemDAOImpl (SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    @CachePut
    public void save(InvoiceItem invoiceItem) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(invoiceItem);
    }


    @Override
    @CacheEvict
    public void delete(InvoiceItem invoiceItem) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(invoiceItem);
    }


    @Override
    @CachePut
    public void update(InvoiceItem invoiceItem) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(invoiceItem);
    }


    @Override
    @Cacheable
    public InvoiceItem getById(Long invoiceItemId) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(InvoiceItem.class, invoiceItemId);
    }


    @Override
    @Cacheable
    public List<InvoiceItem> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_INVOICE_ITEM_A, InvoiceItem.class).getResultList();
    }

    @Override
    @Cacheable
    public long getInvId() {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery(SELECT_ID_FROM_INVOICE_ITEM_WHERE_ID_SELECT_MAX_ID_FROM_INVOICE_ITEM);
        return (long) q.getSingleResult();
    }


    @Override
    @Cacheable
    public InvoiceItem getInvoiceItemByNumber(int number) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_INVOICE_ITEM_A_WHERE_A_NUMBER_CUST_NUMBER, InvoiceItem.class)
                .setParameter("custNumber", number).getSingleResult();
    }


    @Override
    @Cacheable
    public List<InvoiceItem> getInvoiceItemByproductId(Long productId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(FROM_INVOICE_ITEM_S_WHERE_S_PRODUCT_ID_IDPROD, InvoiceItem.class)
                .setParameter("idprod", productId)
                .getResultList();
    }


    @Override

    @Cacheable
    public List<InvoiceItem> getInvoiceItemByinvoiceId(Long invoiceId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(FROM_INVOICE_ITEM_S_WHERE_S_INVOICE_ID_IDINV, InvoiceItem.class)
                .setParameter("idinv", invoiceId)
                .getResultList();
    }


    @Override
    @Cacheable
    public List<InvoiceItem> getInvoiceItems(long productId, long invoiceId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(FROM_INVOICE_ITEM_S_WHERE_S_INVOICE_ID_IDINV_AND_S_PRODUCT_ID_IDPROD, InvoiceItem.class)
                .setParameter("idinv", invoiceId)
                .setParameter("idprod", productId)
                .getResultList();
    }


}