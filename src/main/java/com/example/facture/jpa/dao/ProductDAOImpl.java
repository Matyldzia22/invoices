package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;

import com.example.facture.jpa.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

import java.util.List;

@Repository
@CacheConfig(cacheNames = "application-cache")
public class ProductDAOImpl implements ProductDAO {

    private static final String SELECT_A_FROM_PRODUCT_A = "Select a From Product a";
    private static final String SELECT_A_FROM_PRODUCT_A_WHERE_A_NAME_LIKE_CUST_NAME = "Select a From Product a where a.name like :custName";
    private static final String SELECT_A_FROM_PRODUCT_A_WHERE_A_VAT_CUST_VAT = "Select a From Product a where a.vat = :custVat";
    private static final String SELECT_A_FROM_PRODUCT_A_WHERE_A_BRUTTO_PRICE_CUST_BRUTTO_PRICE = "Select a From Product a where a.bruttoPrice = :custBruttoPrice";
    private static final String SELECT_A_FROM_PRODUCT_A_WHERE_A_NETTO_PRICE_CUST_NETTO_PRICE = "Select a From Product a where a.nettoPrice = :custNettoPrice";

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void save(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(product);
    }


    @Override
    public void delete(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(product);
    }


    @Override
    public void update(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(product);
    }


    @Override
    @Cacheable(key="#idProduct")
    public Product getById(Long idProduct) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(Product.class, idProduct);
    }


    @Override
    @Cacheable
    public List<Product> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_PRODUCT_A, Product.class).getResultList();
    }


    @Override
    @Cacheable(key="#name")
    public Product getProductByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_PRODUCT_A_WHERE_A_NAME_LIKE_CUST_NAME, Product.class)
                .setParameter("custName", name).getSingleResult();
    }


    @Override
    @Cacheable(key="#vat")
    public Product getProductByVat(int vat) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_PRODUCT_A_WHERE_A_VAT_CUST_VAT, Product.class)
                .setParameter("custVat", vat).getSingleResult();
    }


    @Override
    @Cacheable(key="#bruttoPrice")
    public Product getProductByBruttoPrice(double bruttoPrice) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_PRODUCT_A_WHERE_A_BRUTTO_PRICE_CUST_BRUTTO_PRICE, Product.class)
                .setParameter("custBruttoPrice", bruttoPrice).getSingleResult();
    }


    @Override
    @Cacheable(key="#nettoPrice")
    public Product getProductByNettoPrice(double nettoPrice) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_PRODUCT_A_WHERE_A_NETTO_PRICE_CUST_NETTO_PRICE, Product.class)
                .setParameter("custNettoPrice", nettoPrice).getSingleResult();
    }


    @Override
    public List<InvoiceItem> getInvoiceItems(Product product) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM InvoiceItem c JOIN FETCH c.product p where p.id = :id";
        return session.createQuery(hql, InvoiceItem.class)
                .setParameter("id", product.getId())
                .getResultList();
    }


}
