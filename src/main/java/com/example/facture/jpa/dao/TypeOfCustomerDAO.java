package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;
import com.example.facture.jpa.model.TypeOfCustomer;

import java.util.List;

public interface TypeOfCustomerDAO extends BaseDAO<TypeOfCustomer, Long> {

    TypeOfCustomer getTypeOfCustomerByName(String name);

    List<Customer> getCustomers(TypeOfCustomer typeOfCustomer);


}