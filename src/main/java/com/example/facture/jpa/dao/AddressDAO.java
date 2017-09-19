package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;


import com.example.facture.jpa.model.Address;


import java.util.Date;
import java.util.List;

public interface AddressDAO extends BaseDAO<Address, Long> {

    Address getAddressByCity(String city);
    Address getAddressByPostCode(String postCode);
    Address getAddressByStreet(String street);



    List<Invoice> getInvoices(Address address);










}