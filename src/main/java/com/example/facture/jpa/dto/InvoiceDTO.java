package com.example.facture.jpa.dto;
import com.example.facture.jpa.model.Customer;
import com.example.facture.jpa.model.InvoiceItem;
import com.example.facture.jpa.model.Address;


import lombok.Data;

import java.util.Date;
import java.util.List;

public @Data class InvoiceDTO {

    private Long id;
    private String number;
    private Date sellingDate;
    private Date invoiceDate;
    private double sum;
    private Date confirmDate;

    private Customer customer;
    List<InvoiceItem> invoiceItems;
    private Address address;




}