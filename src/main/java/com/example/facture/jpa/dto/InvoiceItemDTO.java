package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.Invoice;
import com.example.facture.jpa.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class InvoiceItemDTO {

    private Long id;
    @NotNull
    @Range(min = 1, message = "Please select a number!")
    private int number;
    private long invoiceId;
    @NotNull
    @Range(min = 1, message = "Please select a product!")
    private long productId;
    private InvoiceDTO invoice2;
    private ProductDTO product;
    private Invoice invoice;

}