package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.Invoice;
import com.example.facture.jpa.model.Product;
import lombok.Data;


public @Data
class InvoiceItemDisplayDTO {

    private Long id;
    private String number;
    private Long invoiceId;
    private Long productId;
    private Invoice invoice;
    private Product product;

}