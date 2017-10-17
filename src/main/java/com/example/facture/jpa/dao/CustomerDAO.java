package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;


import com.example.facture.jpa.model.Customer;


import java.util.List;

public interface CustomerDAO extends BaseDAO<Customer, Long> {

    List<Customer> getAll();

    Customer getCustomerByName(String name);

    Customer getCustomerByFirstName(String firstName);

    Customer getCustomerByLastName(String lastName);

    Customer getCustomerByEmail(String email);

    Customer getCustomerByNip(String nip);

    List<Customer> getCustomerBypriceGroupId(Long priceGroupId);

    List<Customer> getCustomerBytaxBracketId(Long taxBracketId);

    List<Customer> getCustomerBytypeOfCustomerId(Long typeOfCustomerId);

    List<Customer> getCustomers(long priceGroupId, long taxBracketId, long typeOfCustomerId);

    List<Invoice> getInvoices(Customer customer);

    List<Address> getAddresses(Customer customer);


}