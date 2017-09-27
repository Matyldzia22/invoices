package com.example.facture.jpa.dao;
import com.example.facture.jpa.model.*;

import com.example.facture.jpa.model.Invoice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;

import java.util.Date;
import java.util.List;

@Repository
public class InvoiceDAOImpl implements InvoiceDAO {

    @Autowired
    private SessionFactory sessionFactory;

    //Saving Invoice object to database
    @Override
    public void save(Invoice invoice) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(invoice);
    }

    //Deleting Invoice object from database
    @Override
    public void delete(Invoice invoice) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(invoice);
    }

    //Updating Invoice object from database
    @Override
    public void update(Invoice invoice) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(invoice);
    }

    //Getting Invoice object from database by id
    @Override
    @Cacheable("application-cache")
    public Invoice getById(Long idInvoice) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(Invoice.class, idInvoice);
    }

    //Getting all invoices object from database and set to list
    @Override
    @Cacheable("application-cache")
    public List<Invoice> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select a From Invoice a", Invoice.class).getResultList();
    }

    //Getting Invoice object from database by number
    @Override
    @Cacheable("application-cache")
    public Invoice getInvoiceByNumber(String number) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select a From Invoice a where a.number like :custNumber", Invoice.class)
                .setParameter("custNumber", number).getSingleResult();
    }

    //Getting Invoice object from database by sellingDate
    @Override
    @Cacheable("application-cache")
    public List<Invoice> getInvoiceBySellingDate(Date sellingDate) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select a From Invoice a where a.sellingDate = :custSellingDate", Invoice.class)
                .setParameter("custSellingDate", sellingDate).getResultList();
    }

    //Getting Invoice object from database by invoiceDate
    @Override
    @Cacheable("application-cache")
    public List<Invoice> getInvoiceByInvoiceDate(Date invoiceDate) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select a From Invoice a where a.invoiceDate = :custInvoiceDate", Invoice.class)
                .setParameter("custInvoiceDate", invoiceDate).getResultList();
    }



    //Getting Invoice object from database by confirmDate

    @Override
    @Cacheable("application-cache")
    public List<Invoice> getInvoiceByConfirmDate(Date confirmDate) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select a From Invoice a where a.confirmDate = :custConfirmDate", Invoice.class)
                .setParameter("custConfirmDate", confirmDate).getResultList();
    }

    //Getting InvoiceItems objects from Invoice object from database
    @Override
    public List<InvoiceItem> getInvoiceItems(Invoice invoice) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM InvoiceItem c JOIN FETCH c.invoice p where p.id = :id";
        return session.createQuery(hql, InvoiceItem.class)
                .setParameter("id", invoice.getId())
                .getResultList();
    }

    //Getting Invoice object from database by idCustomer

    @Override
    @Cacheable("application-cache")
    public List<Invoice> getInvoiceByIdCustomer(Long idCustomer) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Invoice s where s.customer.id = :idcust";
        return session.createQuery(hql, Invoice.class)
                .setParameter("idcust", idCustomer)
                .getResultList();
    }

    //Getting Invoice object from database by idAddress

    @Override
    @Cacheable("application-cache")
    public List<Invoice> getInvoiceByIdAddress(Long idAddress) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Invoice s where s.address.id = :idaddr";
        return session.createQuery(hql, Invoice.class)
                .setParameter("idaddr", idAddress)
                .getResultList();
    }

    //Getting Invoices from database by idCustomer and idAddress

    @Override
    @Cacheable("application-cache")
    public List<Invoice> getInvoices(long idAddress, long idCustomer){
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Invoice s where s.address.id = :idaddr and s.customer.id = :idcust";
        return session.createQuery(hql, Invoice.class)
                .setParameter("idaddr", idAddress)
                .setParameter("idcust", idCustomer)
                .getResultList();
    }






}