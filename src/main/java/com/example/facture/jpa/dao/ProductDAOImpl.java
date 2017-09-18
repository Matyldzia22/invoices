package com.example.facture.jpa.dao;
import com.example.facture.jpa.model.*;

import com.example.facture.jpa.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private SessionFactory sessionFactory;

    //Saving Product object to database
    @Override
    public void save(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(product);
    }

    //Deleting Product object from database
    @Override
    public void delete(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(product);
    }

    //Updating Product object from database
    @Override
    public void update(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(product);
    }

    //Getting Product object from database by id
    @Override
    @Cacheable("application-cache")
    public Product getById(Long idProduct) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(Product.class, idProduct);
    }

    //Getting all products object from database and set to list
    @Override
    @Cacheable("application-cache")
    public List<Product> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select a From Product a", Product.class).getResultList();
    }

    //Getting Product object from database by name
    @Override
    @Cacheable("application-cache")
    public Product getProductByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select a From Product a where a.name like :custName", Product.class)
                .setParameter("custName", name).getSingleResult();
    }





}