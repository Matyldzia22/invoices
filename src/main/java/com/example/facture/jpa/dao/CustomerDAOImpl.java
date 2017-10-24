package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;

import com.example.facture.jpa.model.Customer;
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
@CacheConfig(cacheNames = "customer-cache")
public class CustomerDAOImpl implements CustomerDAO {

    private static final String SELECT_A_FROM_CUSTOMER_A = "Select a From Customer a";
    private static final String SELECT_A_FROM_CUSTOMER_A_WHERE_A_NAME_LIKE_CUST_NAME = "Select a From Customer a where a.name like :custName";
    private static final String SELECT_A_FROM_CUSTOMER_A_WHERE_A_FIRST_NAME_LIKE_CUST_FIRST_NAME = "Select a From Customer a where a.firstName like :custFirstName";
    private static final String SELECT_A_FROM_CUSTOMER_A_WHERE_A_LAST_NAME_LIKE_CUST_LAST_NAME = "Select a From Customer a where a.lastName like :custLastName";
    private static final String SELECT_A_FROM_CUSTOMER_A_WHERE_A_NIP_LIKE_CUST_NIP = "Select a From Customer a where a.nip like :custNip";
    private static final String SELECT_A_FROM_CUSTOMER_A_WHERE_A_EMAIL_LIKE_CUST_EMAIL = "Select a From Customer a where a.email like :custEmail";
    private static final String FROM_CUSTOMER_S_WHERE_S_PRICE_GROUP_ID_IDPRICEGR_AND_S_TAX_BRACKET_ID_IDTAXBR_AND_S_TYPE_OF_CUSTOMER_ID_IDTYPEOFCUST = "from Customer s where s.priceGroup.id = :idpricegr and s.taxBracket.id = :idtaxbr and s.typeOfCustomer.id =:idtypeofcust";
    private static final String FROM_CUSTOMER_S_WHERE_S_TYPE_OF_CUSTOMER_ID_IDTYPEOFCUST = "from Customer s where s.typeOfCustomer.id = :idtypeofcust";
    private static final String FROM_CUSTOMER_S_WHERE_S_TAX_BRACKET_ID_IDTAXBR = "from Customer s where s.taxBracket.id = :idtaxbr";
    private static final String FROM_CUSTOMER_S_WHERE_S_PRICE_GROUP_ID_IDPRICEGR = "from Customer s where s.priceGroup.id = :idpricegr";
    private static final String FROM_ADDRESS_C_JOIN_FETCH_C_CUSTOMER_P_WHERE_P_ID_ID = "FROM Address c JOIN FETCH c.customer p where p.id = :id";
    private static final String FROM_INVOICE_C_JOIN_FETCH_C_CUSTOMER_P_WHERE_P_ID_ID = "FROM Invoice c JOIN FETCH c.customer p where p.id = :id";


    private SessionFactory sessionFactory;

    @Autowired
    public CustomerDAOImpl (SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    @CachePut
    public void save(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(customer);
    }


    @Override
    @CacheEvict
    public void delete(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(customer);
    }


    @Override
    @CachePut
    public void update(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(customer);
    }


    @Override
    @Cacheable
    public Customer getById(Long customerId) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(Customer.class, customerId);
    }


    @Override
    @Cacheable
    public List<Customer> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_CUSTOMER_A, Customer.class).getResultList();
    }


    @Override
    @Cacheable
    public Customer getCustomerByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_CUSTOMER_A_WHERE_A_NAME_LIKE_CUST_NAME, Customer.class)
                .setParameter("custName", name).getSingleResult();
    }


    @Override
    @Cacheable
    public Customer getCustomerByFirstName(String firstName) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_CUSTOMER_A_WHERE_A_FIRST_NAME_LIKE_CUST_FIRST_NAME, Customer.class)
                .setParameter("custFirstName", firstName).getSingleResult();
    }


    @Override
    @Cacheable
    public Customer getCustomerByLastName(String lastName) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_CUSTOMER_A_WHERE_A_LAST_NAME_LIKE_CUST_LAST_NAME, Customer.class)
                .setParameter("custLastName", lastName).getSingleResult();
    }


    @Override
    @Cacheable
    public Customer getCustomerByNip(String nip) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_CUSTOMER_A_WHERE_A_NIP_LIKE_CUST_NIP, Customer.class)
                .setParameter("custNip", nip).getSingleResult();
    }


    @Override
    @Cacheable
    public Customer getCustomerByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_CUSTOMER_A_WHERE_A_EMAIL_LIKE_CUST_EMAIL, Customer.class)
                .setParameter("custEmail", email).getSingleResult();
    }


    @Override
    public List<Invoice> getInvoices(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(FROM_INVOICE_C_JOIN_FETCH_C_CUSTOMER_P_WHERE_P_ID_ID, Invoice.class)
                .setParameter("id", customer.getId())
                .getResultList();
    }


    @Override
    public List<Address> getAddresses(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(FROM_ADDRESS_C_JOIN_FETCH_C_CUSTOMER_P_WHERE_P_ID_ID, Address.class)
                .setParameter("id", customer.getId())
                .getResultList();
    }


    @Override
    @Cacheable
    public List<Customer> getCustomerBypriceGroupId(Long priceGroupId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(FROM_CUSTOMER_S_WHERE_S_PRICE_GROUP_ID_IDPRICEGR, Customer.class)
                .setParameter("idpricegr", priceGroupId)
                .getResultList();
    }


    @Override
    @Cacheable
    public List<Customer> getCustomerBytaxBracketId(Long taxBracketId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(FROM_CUSTOMER_S_WHERE_S_TAX_BRACKET_ID_IDTAXBR, Customer.class)
                .setParameter("idtaxbr", taxBracketId)
                .getResultList();
    }


    @Override
    @Cacheable
    public List<Customer> getCustomerBytypeOfCustomerId(Long typeOfCustomerId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(FROM_CUSTOMER_S_WHERE_S_TYPE_OF_CUSTOMER_ID_IDTYPEOFCUST, Customer.class)
                .setParameter("idtypeofcust", typeOfCustomerId)
                .getResultList();

    }


    @Override
    @Cacheable
    public List<Customer> getCustomers(long priceGroupId, long taxBracketId, long typeOfCustomerId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(FROM_CUSTOMER_S_WHERE_S_PRICE_GROUP_ID_IDPRICEGR_AND_S_TAX_BRACKET_ID_IDTAXBR_AND_S_TYPE_OF_CUSTOMER_ID_IDTYPEOFCUST, Customer.class)
                .setParameter("idpricegr", priceGroupId)
                .setParameter("idtaxbr", taxBracketId)
                .setParameter("idtypeofcust", typeOfCustomerId)
                .getResultList();
    }


}