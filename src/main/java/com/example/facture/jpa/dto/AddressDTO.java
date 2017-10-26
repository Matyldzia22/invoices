package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.Customer;
import com.example.facture.jpa.model.Invoice;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
public class AddressDTO {

    private Long id;
    @NotBlank
    private String street;
    @NotBlank
    private String number;
    @NotBlank
    private String city;
    @NotBlank
    private String postCode;
    private long customerId;
    private List<Invoice> invoices;
    private Customer customer;

}
