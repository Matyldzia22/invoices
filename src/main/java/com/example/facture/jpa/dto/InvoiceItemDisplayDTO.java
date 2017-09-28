package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.Invoice;
import com.example.facture.jpa.model.Product;
import lombok.Data;


public @Data
class InvoiceItemDisplayDTO {

    private Long id;
    private String number;
    private Long idInvoice;
    private Long idProduct;
    private Invoice invoice;
    private Product product;


}