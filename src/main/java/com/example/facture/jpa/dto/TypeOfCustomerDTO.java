package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
public class TypeOfCustomerDTO {

    private Long id;
    @NotBlank
    private String name;
    private List<Customer> customers;

}