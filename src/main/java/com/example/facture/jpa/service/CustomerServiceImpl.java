package com.example.facture.jpa.service;

import com.example.facture.jpa.dao.*;
import com.example.facture.jpa.dto.*;
import com.example.facture.jpa.model.*;
import com.example.facture.jpa.service.Mapper;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@Transactional

public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private InvoiceDAO invoiceDAO;

    @Autowired
    private MapperFacade mapperFacade;

    @Override
    public void saveCustomer(CustomerDTO customerDTO)  {

        Customer customer = mapperFacade.map(customerDTO, Customer.class);

        customerDAO.save(customer);
    }

    @Override
    public List<CustomerDTO>getAllCustomers(){

        List<CustomerDTO> customers = new ArrayList<>();
        customerDAO.getAll().forEach(customer -> customers.add(mapperFacade.map(customer, CustomerDTO.class)));
        return customers;
    }

    @Override
    public Customer getCustomerById(Long id) {

        return customerDAO.getById(id);
    }

    @Override
    public void deleteCustomer(CustomerDTO customerDTO){

        Customer customer = customerDAO.getById(customerDTO.getId());

        customerDAO.delete(customer);
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO){

        Customer customer2 = customerDAO.getById(customerDTO.getId());

        customerDAO.update(customer2);
    }

    @Override
    public CustomerDTO getCustomerByName(String name){
        return mapperFacade.map(customerDAO.getCustomerByName(name), CustomerDTO.class);
    }

    @Override
    public CustomerDTO getCustomerByLastName(String lastName){
        return mapperFacade.map(customerDAO.getCustomerByFirstName(lastName),CustomerDTO.class);
    }

    @Override
    public CustomerDTO getCustomerByEmail(String email){
        return mapperFacade.map(customerDAO.getCustomerByEmail(email), CustomerDTO.class);
    }

    @Override
    public CustomerDTO getCustomerByNip(String nip){
        return mapperFacade.map(customerDAO.getCustomerByNip(nip), CustomerDTO.class);
    }

   @Override
    public List<Invoice> getInvoices(Customer customer){

        return customerDAO.getInvoices(customer);
   }

   @Override
    public void addInvoice2Customer(Customer customer, Invoice invoice){

       if (invoice.getCustomer() != customer) {
           invoice.setCustomer(customer);
           invoiceDAO.update(invoice);
       }
       if (!customerDAO.getInvoices(customer).contains(invoice)) {
           List<Invoice> invoices = customerDAO.getInvoices(customer);
           invoices.add(invoice);
           customer.setInvoices(invoices);
           customerDAO.update(customer);
       }

   }

   @Override
    public void deleteInvoiceFromCustomer(Customer customer, Invoice invoice){

        if(invoice.getCustomer() == customer){
            invoice.setCustomer(null);
            invoiceDAO.update(invoice);
        }
        if(customerDAO.getInvoices(customer).contains(invoice)){
            List<Invoice> invoices = customerDAO.getInvoices(customer);
            invoices.remove(invoice);
            customer.setInvoices(invoices);
            customerDAO.update(customer);
        }
   }












}
