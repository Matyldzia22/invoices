package com.example.facture.jpa.dao;

import com.example.facture.jpa.dto.ProductDTO;
import com.example.facture.jpa.model.*;

import com.example.facture.jpa.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

import java.util.List;

@Repository
@CacheConfig(cacheNames = "product-cache")
public class ProductDAOImpl implements ProductDAO {

    private static final String SELECT_A_FROM_PRODUCT_A = "Select a From Product a";
    private static final String SELECT_A_FROM_PRODUCT_A_WHERE_A_NAME_LIKE_CUST_NAME = "Select a From Product a where a.name like :custName";
    private static final String SELECT_A_FROM_PRODUCT_A_WHERE_A_VAT_CUST_VAT = "Select a From Product a where a.vat = :custVat";
    private static final String SELECT_A_FROM_PRODUCT_A_WHERE_A_BRUTTO_PRICE_CUST_BRUTTO_PRICE = "Select a From Product a where a.bruttoPrice = :custBruttoPrice";
    private static final String SELECT_A_FROM_PRODUCT_A_WHERE_A_NETTO_PRICE_CUST_NETTO_PRICE = "Select a From Product a where a.nettoPrice = :custNettoPrice";
    private static final String FROM_INVOICE_ITEM_C_JOIN_FETCH_C_PRODUCT_P_WHERE_P_ID_ID = "FROM InvoiceItem c JOIN FETCH c.product p where p.id = :id";
    private static final String SELECT_ID_FROM_PRODUCT_WHERE_ID_SELECT_MAX_ID_FROM_PRODUCT = "SELECT(id) FROM Product WHERE id = ( SELECT MAX(id) FROM Product)";
    private static final String SELECT_NETTO_PRICE_PRODUCTS = "SELECT ((c.bruttoPrice - ((c.bruttoPrice * c.vat))/100)) AS netto FROM Product c WHERE c.id =:id";


    private SessionFactory sessionFactory;

    public ProductDAOImpl (SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    @CachePut
    public void save(Product product) {
        Session session = sessionFactory.getCurrentSession();
        product.setNettoPrice((product.getBruttoPrice() - ((product.getBruttoPrice() * product.getVat())) / 100));
        session.persist(product);
    }


    @Override
    @CacheEvict
    public void delete(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(product);
    }


    @Override
    @CachePut
    public void update(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(product);
    }


    @Override
    @Cacheable
    public Product getById(Long productId) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(Product.class, productId);
    }


    @Override
    @Cacheable
    public List<Product> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_PRODUCT_A, Product.class).getResultList();
    }

    @Override
    @Cacheable
    public long getProdId() {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery(SELECT_ID_FROM_PRODUCT_WHERE_ID_SELECT_MAX_ID_FROM_PRODUCT);
        return (long) q.getSingleResult();
    }


    @Override
    @Cacheable
    public Product getProductByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_PRODUCT_A_WHERE_A_NAME_LIKE_CUST_NAME, Product.class)
                .setParameter("custName", name).getSingleResult();
    }


    @Override
    @Cacheable
    public Product getProductByVat(int vat) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_PRODUCT_A_WHERE_A_VAT_CUST_VAT, Product.class)
                .setParameter("custVat", vat).getSingleResult();
    }


    @Override
    @Cacheable
    public Product getProductByBruttoPrice(double bruttoPrice) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_PRODUCT_A_WHERE_A_BRUTTO_PRICE_CUST_BRUTTO_PRICE, Product.class)
                .setParameter("custBruttoPrice", bruttoPrice).getSingleResult();
    }


    @Override
    @Cacheable
    public Product getProductByNettoPrice(double nettoPrice) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_PRODUCT_A_WHERE_A_NETTO_PRICE_CUST_NETTO_PRICE, Product.class)
                .setParameter("custNettoPrice", nettoPrice).getSingleResult();
    }


    @Override
    public List<InvoiceItem> getInvoiceItems(Product product) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(FROM_INVOICE_ITEM_C_JOIN_FETCH_C_PRODUCT_P_WHERE_P_ID_ID, InvoiceItem.class)
                .setParameter("id", product.getId())
                .getResultList();
    }

    @Override
    @Cacheable
    public double getNettoPrice(long id) {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery(SELECT_NETTO_PRICE_PRODUCTS);
        q.setParameter("id", id);


        return (double) q.getSingleResult();
    }


}
