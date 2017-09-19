package com.example.facture.jpa.service;

import com.example.facture.jpa.dto.*;
import com.example.facture.jpa.model.*;
import java.util.List;

public interface AddressService {

    List<AddressDTO> getAllAddresses();

    Address getAddressById(Long id);

    void deleteAddress(AddressDTO addressDTO);

    void updateAddress(AddressDTO addressDTO);
    AddressDTO getAddressByCity(String city);

    void addInvoice2Address(Address address, Invoice invoice);
    void deleteInvoiceFromAddress(Address address, Invoice invoice);
    List<Invoice> getInvoices(Address address);






}