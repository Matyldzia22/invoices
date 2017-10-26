package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class TaxBracketDTO {

    private Long id;
    @NotNull
    private int number;
    private List<Customer> customers;

    @Override
    public String toString() {
        return String.format("TaxBracketDTO[%d_%d]", id, number);
    }

}