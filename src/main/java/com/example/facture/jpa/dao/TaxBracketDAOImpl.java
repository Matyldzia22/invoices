package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import java.util.List;



@Repository
public class TaxBracketDAOImpl implements TaxBracketDAO {

    @Autowired
    private SessionFactory sessionFactory;

    //Saving TaxBracket object to database
    @Override
    public void save(TaxBracket taxBracket) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(taxBracket);
    }

    //Deleting TaxBracket object from database
    @Override
    public void delete(TaxBracket taxBracket) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(taxBracket);
    }

    //Updating TaxBracket object from database
    @Override
    public void update(TaxBracket taxBracket) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(taxBracket);
    }

    //Getting TaxBracket object from database by id
    @Override
    @Cacheable("application-cache")
    public TaxBracket getById(Long idTaxBracket) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(TaxBracket.class, idTaxBracket);
    }

    @Override
    @Cacheable("application-cache")
    public List<TaxBracket> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select a From TaxBracket a", TaxBracket.class).getResultList();
    }

    @Override
    @Cacheable("application-cache")
    public TaxBracket getTaxBracketByNumber(int number){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select a From TaxBracket a where a.number = :custNumber", TaxBracket.class)
                .setParameter("custNumber", number).getSingleResult();
    }

    //Getting Customers objects from TaxBracket object from database
    @Override
    public List<Customer> getCustomers(TaxBracket taxBracket) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Customer c JOIN FETCH c.taxBracket p where p.id = :id";
        return session.createQuery(hql, Customer.class)
                .setParameter("id", taxBracket.getId())
                .getResultList();
    }
}