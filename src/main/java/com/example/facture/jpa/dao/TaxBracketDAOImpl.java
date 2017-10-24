package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@CacheConfig(cacheNames = "taxBracket-cache")
public class TaxBracketDAOImpl implements TaxBracketDAO {

    private static final String SELECT_A_FROM_TAX_BRACKET_A = "Select a From TaxBracket a";
    private static final String SELECT_A_FROM_TAX_BRACKET_A_WHERE_A_NUMBER_CUST_NUMBER = "Select a From TaxBracket a where a.number = :custNumber";
    private static final String FROM_CUSTOMER_C_JOIN_FETCH_C_TAX_BRACKET_P_WHERE_P_ID_ID = "FROM Customer c JOIN FETCH c.taxBracket p where p.id = :id";



    private SessionFactory sessionFactory;

    @Autowired
    public TaxBracketDAOImpl (SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    @CachePut
    public void save(TaxBracket taxBracket) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(taxBracket);
    }


    @Override
    @CacheEvict
    public void delete(TaxBracket taxBracket) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(taxBracket);
    }


    @Override
    @CachePut
    public void update(TaxBracket taxBracket) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(taxBracket);
    }


    @Override
    @Cacheable
    public TaxBracket getById(Long taxBracketId) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(TaxBracket.class, taxBracketId);
    }

    @Override
    @Cacheable
    public List<TaxBracket> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_TAX_BRACKET_A, TaxBracket.class).getResultList();
    }

    @Override
    @Cacheable
    public TaxBracket getTaxBracketByNumber(int number) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_TAX_BRACKET_A_WHERE_A_NUMBER_CUST_NUMBER, TaxBracket.class)
                .setParameter("custNumber", number).getSingleResult();
    }


    @Override
    public List<Customer> getCustomers(TaxBracket taxBracket) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(FROM_CUSTOMER_C_JOIN_FETCH_C_TAX_BRACKET_P_WHERE_P_ID_ID, Customer.class)
                .setParameter("id", taxBracket.getId())
                .getResultList();
    }
}
