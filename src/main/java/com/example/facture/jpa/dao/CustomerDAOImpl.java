package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;

import com.example.facture.jpa.model.Customer;
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
public class CustomerDAOImpl implements CustomerDAO {

    private static final String SELECT_A_FROM_CUSTOMER_A = "Select a From Customer a";
    private static final String SELECT_A_FROM_CUSTOMER_A_WHERE_A_NAME_LIKE_CUST_NAME = "Select a From Customer a where a.name like :custName";
    private static final String SELECT_A_FROM_CUSTOMER_A_WHERE_A_FIRST_NAME_LIKE_CUST_FIRST_NAME = "Select a From Customer a where a.firstName like :custFirstName";
    private static final String SELECT_A_FROM_CUSTOMER_A_WHERE_A_LAST_NAME_LIKE_CUST_LAST_NAME = "Select a From Customer a where a.lastName like :custLastName";
    private static final String SELECT_A_FROM_CUSTOMER_A_WHERE_A_NIP_LIKE_CUST_NIP = "Select a From Customer a where a.nip like :custNip";
    private static final String SELECT_A_FROM_CUSTOMER_A_WHERE_A_EMAIL_LIKE_CUST_EMAIL = "Select a From Customer a where a.email like :custEmail";

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void save(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(customer);
    }


    @Override
    public void delete(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(customer);
    }


    @Override
    public void update(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(customer);
    }


    @Override
    @Cacheable
    public Customer getById(Long idCustomer) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(Customer.class, idCustomer);
    }


    @Override
    @Cacheable
    public List<Customer> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_CUSTOMER_A, Customer.class).getResultList();
    }


    @Override
    @Cacheable(key="#name")
    public Customer getCustomerByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_CUSTOMER_A_WHERE_A_NAME_LIKE_CUST_NAME, Customer.class)
                .setParameter("custName", name).getSingleResult();
    }


    @Override
    @Cacheable(key="#firstName")
    public Customer getCustomerByFirstName(String firstName) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_CUSTOMER_A_WHERE_A_FIRST_NAME_LIKE_CUST_FIRST_NAME, Customer.class)
                .setParameter("custFirstName", firstName).getSingleResult();
    }


    @Override
    @Cacheable(key="#lastName")
    public Customer getCustomerByLastName(String lastName) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_CUSTOMER_A_WHERE_A_LAST_NAME_LIKE_CUST_LAST_NAME, Customer.class)
                .setParameter("custLastName", lastName).getSingleResult();
    }


    @Override
    @Cacheable(key="#nip")
    public Customer getCustomerByNip(String nip) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_CUSTOMER_A_WHERE_A_NIP_LIKE_CUST_NIP, Customer.class)
                .setParameter("custNip", nip).getSingleResult();
    }


    @Override
    @Cacheable(key="#email")
    public Customer getCustomerByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(SELECT_A_FROM_CUSTOMER_A_WHERE_A_EMAIL_LIKE_CUST_EMAIL, Customer.class)
                .setParameter("custEmail", email).getSingleResult();
    }


    @Override
    public List<Invoice> getInvoices(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Invoice c JOIN FETCH c.customer p where p.id = :id";
        return session.createQuery(hql, Invoice.class)
                .setParameter("id", customer.getId())
                .getResultList();
    }


    @Override
    public List<Address> getAddresses(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Address c JOIN FETCH c.customer p where p.id = :id";
        return session.createQuery(hql, Address.class)
                .setParameter("id", customer.getId())
                .getResultList();
    }


    @Override
    @Cacheable(key="#idPriceGroup")
    public List<Customer> getCustomerByIdPriceGroup(Long idPriceGroup) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Customer s where s.priceGroup.id = :idpricegr";
        return session.createQuery(hql, Customer.class)
                .setParameter("idpricegr", idPriceGroup)
                .getResultList();
    }


    @Override
    @Cacheable(key="#idTaxBracket")
    public List<Customer> getCustomerByIdTaxBracket(Long idTaxBracket) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Customer s where s.taxBracket.id = :idtaxbr";
        return session.createQuery(hql, Customer.class)
                .setParameter("idtaxbr", idTaxBracket)
                .getResultList();
    }


    @Override
    @Cacheable(key="#idTypeOfCustomer")
    public List<Customer> getCustomerByIdTypeOfCustomer(Long idTypeOfCustomer) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Customer s where s.typeOfCustomer.id = :idtypeofcust";
        return session.createQuery(hql, Customer.class)
                .setParameter("idtypeofcust", idTypeOfCustomer)
                .getResultList();

    }


    @Override
    @Cacheable(key="{#idPriceGroup, #idTaxBracket, #idTypeOfCustomer}")
    public List<Customer> getCustomers(long idPriceGroup, long idTaxBracket, long idTypeOfCustomer) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Customer s where s.priceGroup.id = :idpricegr and s.taxBracket.id = :idtaxbr and s.typeOfCustomer.id =:idtypeofcust";
        return session.createQuery(hql, Customer.class)
                .setParameter("idpricegr", idPriceGroup)
                .setParameter("idtaxbr", idTaxBracket)
                .setParameter("idtypeofcust", idTypeOfCustomer)
                .getResultList();
    }


}