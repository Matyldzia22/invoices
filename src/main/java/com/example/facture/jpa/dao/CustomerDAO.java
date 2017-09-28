package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;


import com.example.facture.jpa.model.Customer;


import java.util.List;

public interface CustomerDAO extends BaseDAO<Customer, Long> {

    Customer getCustomerByName(String name);

    Customer getCustomerByFirstName(String firstName);

    Customer getCustomerByLastName(String lastName);

    Customer getCustomerByEmail(String email);

    Customer getCustomerByNip(String nip);

    List<Customer> getCustomerByIdPriceGroup(Long idPriceGroup);

    List<Customer> getCustomerByIdTaxBracket(Long idTaxBracket);

    List<Customer> getCustomerByIdTypeOfCustomer(Long idTypeOfCustomer);

    List<Customer> getCustomers(long idPriceGroup, long idTaxBracket, long idTypeOfCustomer);

    List<Invoice> getInvoices(Customer customer);

    List<Address> getAddresses(Customer customer);


}