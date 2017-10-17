package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.Invoice;
import com.example.facture.jpa.model.Address;
import com.example.facture.jpa.model.TypeOfCustomer;
import com.example.facture.jpa.model.TaxBracket;
import com.example.facture.jpa.model.PriceGroup;

import lombok.Data;

import java.util.List;

public @Data
class CustomerDisplayDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String nip;
    private String phoneNumber;
    private String name;
    private Long taxBracketId;
    private Long priceGroupId;
    private Long typeOfCustomerId;
    private PriceGroupDTO priceGroup;
    private TaxBracketDTO taxBracket;
    private TypeOfCustomerDTO typeOfCustomer;
    private List<Invoice> invoices;
    private List<Address> addresses;


}