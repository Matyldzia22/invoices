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
    private TaxBracketDAO taxBracketDAO;

    @Autowired
    private PriceGroupDAO priceGroupDAO;

    @Autowired
    private TypeOfCustomerDAO typeOfCustomerDAO;

    @Autowired
    private MapperFacade mapperFacade;

    @Override
    public void saveCustomer(CustomerDTO customerDTO)  {

        Customer customer = mapperFacade.map(customerDTO, Customer.class);
        addPriceGroup2Customer(customer, priceGroupDAO.getById(customerDTO.getIdPriceGroup()));
        addTaxBracket2Customer(customer, taxBracketDAO.getById(customerDTO.getIdTaxBracket()));
        addTypeOfCustomer2Customer(customer, typeOfCustomerDAO.getById(customerDTO.getIdTypeOfCustomer()));

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

    @Override
    public void addTaxBracket2Customer(Customer customer, TaxBracket taxBracket) {
        if (customer.getTaxBracket() != taxBracket) {
            customer.setTaxBracket(taxBracket);
            customerDAO.update(customer);
        }
        if (!taxBracketDAO.getCustomers(taxBracket).contains(customer)) {
            List<Customer> customers = taxBracketDAO.getCustomers(taxBracket);
            customers.add(customer);
            taxBracket.setCustomers(customers);
            taxBracketDAO.update(taxBracket);
        }
    }

    @Override
    public void deleteTaxBracketFromCustomer(Customer customer, TaxBracket taxBracket) {
        if (customer.getTaxBracket() == taxBracket) {
            customer.setTaxBracket(null);
            customerDAO.update(customer);
        }
        if (taxBracketDAO.getCustomers(taxBracket).contains(customer)) {
            List<Customer> customers = taxBracketDAO.getCustomers(taxBracket);
            customers.remove(customer);
            taxBracket.setCustomers(customers);
            taxBracketDAO.update(taxBracket);
        }
    }

    @Override
    public void addPriceGroup2Customer(Customer customer, PriceGroup priceGroup) {
        if (customer.getPriceGroup() != priceGroup) {
            customer.setPriceGroup(priceGroup);
            customerDAO.update(customer);
        }
        if (!priceGroupDAO.getCustomers(priceGroup).contains(customer)) {
            List<Customer> customers = priceGroupDAO.getCustomers(priceGroup);
            customers.add(customer);
            priceGroup.setCustomers(customers);
            priceGroupDAO.update(priceGroup);
        }
    }

    @Override
    public void deletePriceGroupFromCustomer(Customer customer, PriceGroup priceGroup) {
        if (customer.getPriceGroup() == priceGroup) {
            customer.setPriceGroup(null);
            customerDAO.update(customer);
        }
        if (priceGroupDAO.getCustomers(priceGroup).contains(customer)) {
            List<Customer> customers = priceGroupDAO.getCustomers(priceGroup);
            customers.remove(customer);
            priceGroup.setCustomers(customers);
            priceGroupDAO.update(priceGroup);
        }
    }

    @Override
    public void addTypeOfCustomer2Customer(Customer customer, TypeOfCustomer typeOfCustomer) {
        if (customer.getTypeOfCustomer() != typeOfCustomer) {
            customer.setTypeOfCustomer(typeOfCustomer);
            customerDAO.update(customer);
        }
        if (!typeOfCustomerDAO.getCustomers(typeOfCustomer).contains(customer)) {
            List<Customer> customers = typeOfCustomerDAO.getCustomers(typeOfCustomer);
            customers.add(customer);
            typeOfCustomer.setCustomers(customers);
            typeOfCustomerDAO.update(typeOfCustomer);
        }
    }

    @Override
    public void deleteTypeOfCustomerFromCustomer(Customer customer, TypeOfCustomer typeOfCustomer) {
        if (customer.getTypeOfCustomer() == typeOfCustomer) {
            customer.setTypeOfCustomer(null);
            customerDAO.update(customer);
        }
        if (typeOfCustomerDAO.getCustomers(typeOfCustomer).contains(customer)) {
            List<Customer> customers = typeOfCustomerDAO.getCustomers(typeOfCustomer);
            customers.remove(customer);
            typeOfCustomer.setCustomers(customers);
            typeOfCustomerDAO.update(typeOfCustomer);
        }
    }

    @Override
    public List<Customer> getCustomers( long idPriceGroup, long idTaxBracket,long idTypeOfCustomer) {
        return customerDAO.getCustomers(idPriceGroup, idTaxBracket, idTypeOfCustomer);
    }
















}
