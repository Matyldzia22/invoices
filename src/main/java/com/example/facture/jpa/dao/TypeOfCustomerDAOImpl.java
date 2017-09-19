package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;



@Repository
public class TypeOfCustomerDAOImpl implements TypeOfCustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    //Saving TypeOfCustomer object to database
    @Override
    public void save(TypeOfCustomer typeOfCustomer) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(typeOfCustomer);
    }

    //Deleting TypeOfCustomer object from database
    @Override
    public void delete(TypeOfCustomer typeOfCustomer) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(typeOfCustomer);
    }

    //Updating TypeOfCustomer object from database
    @Override
    public void update(TypeOfCustomer typeOfCustomer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(typeOfCustomer);
    }

    //Getting TypeOfCustomer object from database by id
    @Override
    @Cacheable("application-cache")
    public TypeOfCustomer getById(Long idTypeOfCustomer) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(TypeOfCustomer.class, idTypeOfCustomer);
    }

    @Override
    @Cacheable("application-cache")
    public List<TypeOfCustomer> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select a From TypeOfCustomer a", TypeOfCustomer.class).getResultList();
    }

    @Override
    @Cacheable("application-cache")
    public TypeOfCustomer getTypeOfCustomerByName(String name){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select a From TypeOfCustomer a where a.name like :custName", TypeOfCustomer.class)
                .setParameter("custName", name).getSingleResult();
    }

    //Getting Customers objects from TypeOfCustomer object from database
    @Override
    public List<Customer> getCustomers(TypeOfCustomer typeOfCustomer) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Customer c JOIN FETCH c.typeOfCustomer p where p.id = :id";
        return session.createQuery(hql, Customer.class)
                .setParameter("id", typeOfCustomer.getId())
                .getResultList();
    }
}