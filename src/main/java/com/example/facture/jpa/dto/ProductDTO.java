package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.InvoiceItem;
import lombok.Data;

import java.util.List;


public @Data
class ProductDTO {

    private Long id;
    private String name;
    private double nettoPrice;
    private int vat;
    private double bruttoPrice;
    private List<InvoiceItem> invoiceItems;


}