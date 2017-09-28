package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.Customer;
import com.example.facture.jpa.model.Invoice;

import lombok.Data;

import java.util.List;

public @Data
class AddressDTO {

    private Long id;
    private String street;
    private String number;
    private String city;
    private String postCode;
    private List<Invoice> invoices;
    private Customer customer;
    private CustomerDTO customer2;
    private long idCustomer;

    public AddressDTO(String street, String number, String city, String postCode, CustomerDTO customer2) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.postCode = postCode;
        this.customer2 = customer2;

    }

    public AddressDTO(String street, String number, String city, String postCode, long idCustomer) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.postCode = postCode;
        this.idCustomer = idCustomer;

    }


}
