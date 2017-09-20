package com.example.facture.jpa.service;

import com.example.facture.jpa.dto.*;
import com.example.facture.jpa.model.*;
import java.util.List;

public interface CustomerService {

    void saveCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getAllCustomers();

    Customer getCustomerById(Long id);

    void deleteCustomer(CustomerDTO customerDTO);

    void updateCustomer(CustomerDTO customerDTO);
    CustomerDTO getCustomerByName(String name);
    CustomerDTO getCustomerByLastName(String lastName);
    CustomerDTO getCustomerByNip(String nip);
    CustomerDTO getCustomerByEmail(String email);

    void addInvoice2Customer(Customer customer, Invoice invoice);
    void deleteInvoiceFromCustomer(Customer customer, Invoice invoice);
    List<Invoice> getInvoices(Customer customer);


}