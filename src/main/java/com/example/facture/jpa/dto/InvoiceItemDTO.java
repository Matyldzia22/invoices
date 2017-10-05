package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.Invoice;
import com.example.facture.jpa.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InvoiceItemDTO {

    private Long id;
    private int number;
    private long idInvoice;
    private long idProduct;
    private InvoiceDTO invoice;
    private ProductDTO product;



}