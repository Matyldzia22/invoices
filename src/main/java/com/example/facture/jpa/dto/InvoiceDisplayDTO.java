package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.Customer;
import com.example.facture.jpa.model.InvoiceItem;
import com.example.facture.jpa.model.Address;


import lombok.Data;

import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
public @Data
class InvoiceDisplayDTO {

    private Long id;
    private String number;
    private Date sellingDate;
    private Date invoiceDate;
    private double sum;
    private Date confirmDate;
    private Long idAddress;
    private Long idCustomer;
    private Customer customer;
    private Address address;
    private List<InvoiceItem> invoiceItems;


}