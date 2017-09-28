package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@CacheConfig(cacheNames = "application-cache")
public class TaxBracketDAOImpl implements TaxBracketDAO {

    private static final String SELECT_A_FROM_TAX_BRACKET_A = "Select a From TaxBracket a";
    private static final String SELECT_A_FROM_TAX_BRACKET_A_WHERE_A_NUMBER_CUST_NUMBER = "Select a From TaxBracket a where a.number = :custNumber";


    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void save(TaxBracket taxBracket) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(taxBracket);
    }


    @Override
    public void delete(TaxBracket taxBracket) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(taxBracket);
    }


    @Override
    public void update(TaxBracket taxBracket) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(taxBracket);
    }


    @Override
    @Cacheable(key="#idTaxBracket")
    public TaxBracket getById(Long idTaxBracket) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(TaxBracket.class, idTaxBracket);
    }

    @Override
    @Cacheable
    public List<TaxBracket> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_TAX_BRACKET_A, TaxBracket.class).getResultList();
    }

    @Override
    @Cacheable(key="#number")
    public TaxBracket getTaxBracketByNumber(int number) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_TAX_BRACKET_A_WHERE_A_NUMBER_CUST_NUMBER, TaxBracket.class)
                .setParameter("custNumber", number).getSingleResult();
    }


    @Override
    public List<Customer> getCustomers(TaxBracket taxBracket) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Customer c JOIN FETCH c.taxBracket p where p.id = :id";
        return session.createQuery(hql, Customer.class)
                .setParameter("id", taxBracket.getId())
                .getResultList();
    }
}
