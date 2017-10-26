package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.InvoiceItem;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private double nettoPrice;
    @Range(min=5,max=27)
    @NotNull
    private int vat;
    @DecimalMin("0.1")
    @NotNull
    private double bruttoPrice;
    private List<InvoiceItem> invoiceItems;

}