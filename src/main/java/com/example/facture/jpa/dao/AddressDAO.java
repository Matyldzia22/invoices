package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;


import com.example.facture.jpa.model.Address;


import java.util.Date;
import java.util.List;

public interface AddressDAO extends BaseDAO<Address, Long> {

    List<Address> getAll();

    List<Address> getAddressByCity(String city);

    List<Address> getAddressByStreet(String street);

    List<Address> getAddressByPostCode(String postCode);

    List<Address> getAddresses(long customerId);

    List<Invoice> getInvoices(Address address);


}