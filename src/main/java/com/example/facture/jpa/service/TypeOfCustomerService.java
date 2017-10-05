package com.example.facture.jpa.service;

import com.example.facture.jpa.dto.*;
import com.example.facture.jpa.model.*;

import java.util.Date;
import java.util.List;

public interface TypeOfCustomerService {

    List<TypeOfCustomer> getAllTypeOfCustomerss();

    List<TypeOfCustomerDTO> getAllTypeOfCustomers();

    TypeOfCustomer getTypeOfCustomerById(Long id);

    void saveTypeOfCustomer(TypeOfCustomerDTO typeOfCustomerDTO);

    void deleteTypeOfCustomer(TypeOfCustomerDTO typeOfCustomerDTO);

    void updateTypeOfCustomer(TypeOfCustomerDTO typeOfCustomerDTO);

    TypeOfCustomerDTO getTypeOfCustomerByName(String name);

    void addCustomer2TypeOfCustomer(TypeOfCustomer typeOfCustomer, Customer customer);

    void deleteCustomerFromTypeOfCustomer(TypeOfCustomer typeOfCustomer, Customer customer);

    List<Customer> getCustomers(TypeOfCustomer typeOfCustomer);


}