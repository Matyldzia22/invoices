package com.example.facture.jpa.service;

import com.example.facture.jpa.dto.*;
import com.example.facture.jpa.model.*;

import java.util.Date;
import java.util.List;

public interface TypeOfAddressService {

    List<TypeOfAddress> getAllTypeOfAddressess();

    List<TypeOfAddressDTO> getAllTypeOfAddresses();

    TypeOfAddress getTypeOfAddressById(Long id);

    void saveTypeOfAddress(TypeOfAddressDTO typeOfAddressDTO);

    void deleteTypeOfAddress(TypeOfAddressDTO typeOfAddressDTO);

    void updateTypeOfAddress(TypeOfAddressDTO typeOfAddressDTO);

    TypeOfAddressDTO getTypeOfAddressByName(String name);

    void addAddress2TypeOfAddress(TypeOfAddress typeOfAddress, Address address);

    void deleteAddressFromTypeOfAddress(TypeOfAddress typeOfAddress, Address address);

    List<Address> getAddresses(TypeOfAddress typeOfAddress);


}