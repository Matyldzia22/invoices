package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.Invoice;
import com.example.facture.jpa.model.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
public class PriceGroupDTO {

    private Long id;
    @NotNull
    @Range(min = 1, max = 100)
    private int discount;
    private Invoice invoice;
    @NotBlank
    private String name;
    private List<Customer> customers;

}