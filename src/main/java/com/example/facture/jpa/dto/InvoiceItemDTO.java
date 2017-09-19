package com.example.facture.jpa.dto;
import com.example.facture.jpa.model.Invoice;
import com.example.facture.jpa.model.Product;
import lombok.Data;



public @Data class InvoiceItemDTO {

    private Long id;
    private String number;


   private Invoice invoice;
   private Product product;




}