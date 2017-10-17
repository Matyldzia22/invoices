package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.Customer;
import com.example.facture.jpa.model.Invoice;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AddressDTO {

    private Long id;
    private String street;
    private String number;
    private String city;
    private String postCode;
    private List<Invoice> invoices;
    private Customer customer;
    private long customerId;


}
