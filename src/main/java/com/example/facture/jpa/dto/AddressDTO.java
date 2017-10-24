package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.Customer;
import com.example.facture.jpa.model.Invoice;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
public class AddressDTO {

    private Long id;
    @Pattern(regexp = "[A-Za-z]+", message = "Street is incorrect. Use only letters!")
    private String street;
    private String number;
    @Pattern(regexp = "[A-Za-z]+", message = "City is incorrect. Use only letters!")
    private String city;
    private String postCode;
    private long customerId;
    private List<Invoice> invoices;
    private Customer customer;

}
