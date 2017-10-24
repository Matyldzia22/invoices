package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
public class TypeOfCustomerDTO {

    private Long id;
    @Pattern(regexp = "[A-Za-z]+", message = "Name is incorrect. Use only letters!")
    private String name;
    private List<Customer> customers;

}