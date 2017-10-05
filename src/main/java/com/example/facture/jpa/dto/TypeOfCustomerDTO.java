package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TypeOfCustomerDTO {

    private Long id;
    private String name;
    private List<Customer> customers;


}