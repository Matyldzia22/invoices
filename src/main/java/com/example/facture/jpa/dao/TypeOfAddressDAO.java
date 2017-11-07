package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;
import com.example.facture.jpa.model.TypeOfAddress;

import java.util.List;

public interface TypeOfAddressDAO extends BaseDAO<TypeOfAddress, Long> {

    TypeOfAddress getTypeOfAddressByName(String name);

    List<Address> getAddresses(TypeOfAddress typeOfAddress);


}