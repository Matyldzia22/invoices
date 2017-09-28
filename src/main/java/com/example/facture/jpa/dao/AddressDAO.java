package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;


import com.example.facture.jpa.model.Address;


import java.util.Date;
import java.util.List;

public interface AddressDAO extends BaseDAO<Address, Long> {


    List<Address> getAddressByCity(String city);

    List<Address> getAddressByStreet(String street);

    List<Address> getAddressByPostCode(String postCode);

    List<Address> getAddressByIdCustomer(Long idCustomer);

    List<Address> getAddresses(long idCustomer);


    List<Invoice> getInvoices(Address address);


}