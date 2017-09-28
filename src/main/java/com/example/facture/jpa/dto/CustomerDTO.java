package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.Invoice;
import com.example.facture.jpa.model.Address;
import com.example.facture.jpa.model.TypeOfCustomer;
import com.example.facture.jpa.model.TaxBracket;
import com.example.facture.jpa.model.PriceGroup;

import lombok.Data;

import java.util.List;

public @Data
class CustomerDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String nip;
    private String phoneNumber;
    private String name;
    private PriceGroup priceGroup;
    private TaxBracket taxBracket;
    private TypeOfCustomer typeOfCustomer;
    private PriceGroupDTO priceGroup2;
    private TaxBracketDTO taxBracket2;
    private TypeOfCustomerDTO typeOfCustomer2;
    private List<Invoice> invoices;
    private List<Address> addresses;
    private long idTypeOfCustomer;
    private long idPriceGroup;
    private long idTaxBracket;


    public CustomerDTO(String firstName, String lastName, String email, String nip, String phoneNumber, String name, PriceGroupDTO priceGroup2, TypeOfCustomerDTO typeOfCustomer2, TaxBracketDTO taxBracket2) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.nip = nip;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.priceGroup2 = priceGroup2;
        this.taxBracket2 = taxBracket2;
        this.typeOfCustomer2 = typeOfCustomer2;

    }

    public CustomerDTO(String firstName, String lastName, String email, String nip, String phoneNumber, String name, long idTaxBracket, long idTypeOfCustomer, long idPriceGroup) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.nip = nip;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.idPriceGroup = idPriceGroup;
        this.idTaxBracket = idTaxBracket;
        this.idTypeOfCustomer = idTypeOfCustomer;


    }


}