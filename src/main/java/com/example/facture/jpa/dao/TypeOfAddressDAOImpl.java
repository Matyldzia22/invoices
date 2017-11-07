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
@CacheConfig(cacheNames = "typeOfAddress-cache")
public class TypeOfAddressDAOImpl implements TypeOfAddressDAO {

    private static final String SELECT_A_FROM_TYPE_OF_ADDRESS_A = "Select a From TypeOfAddress a";
    private static final String SELECT_A_FROM_TYPE_OF_ADDRESS_A_WHERE_A_NAME_LIKE_CUST_NAME = "Select a From TypeOfAddress a where a.name like :custName";
    private static final String FROM_ADDRESS_C_JOIN_FETCH_C_TYPE_OF_ADDRESS_P_WHERE_P_ID_ID = "FROM Address c JOIN FETCH c.typeOfAddress p where p.id = :id";


    private SessionFactory sessionFactory;

    public TypeOfAddressDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    @CachePut
    public void save(TypeOfAddress typeOfAddress) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(typeOfAddress);
    }


    @Override
    @CacheEvict
    public void delete(TypeOfAddress typeOfAddress) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(typeOfAddress);
    }


    @Override
    @CachePut
    public void update(TypeOfAddress typeOfAddress) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(typeOfAddress);
    }


    @Override
    @Cacheable
    public TypeOfAddress getById(Long typeOfAddressId) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(TypeOfAddress.class, typeOfAddressId);
    }

    @Override
    @Cacheable
    public List<TypeOfAddress> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_TYPE_OF_ADDRESS_A, TypeOfAddress.class).getResultList();
    }

    @Override
    @Cacheable
    public TypeOfAddress getTypeOfAddressByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_TYPE_OF_ADDRESS_A_WHERE_A_NAME_LIKE_CUST_NAME, TypeOfAddress.class)
                .setParameter("custName", name).getSingleResult();
    }


    @Override
    public List<Address> getAddresses(TypeOfAddress typeOfAddress) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(FROM_ADDRESS_C_JOIN_FETCH_C_TYPE_OF_ADDRESS_P_WHERE_P_ID_ID, Address.class)
                .setParameter("id", typeOfAddress.getId())
                .getResultList();
    }
}
