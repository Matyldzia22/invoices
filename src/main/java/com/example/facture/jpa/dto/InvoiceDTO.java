package com.example.facture.jpa.dto;
import com.example.facture.jpa.model.Customer;
import com.example.facture.jpa.model.InvoiceItem;
import com.example.facture.jpa.model.Address;


import lombok.Data;

import java.util.Date;
import java.util.List;

public @Data class InvoiceDTO {

    private Long id;
    public InvoiceDTO(String number, Date sellingDate, Date invoiceDate, double sum, Date confirmDate, AddressDTO address2, CustomerDTO customer2) {
        this.number = number;
        this.sellingDate = sellingDate;
        this.sum = sum;
        this.confirmDate = confirmDate;
        this.invoiceDate = invoiceDate;
        this.address2 = address2;
        this.customer2 = customer2;

    }

    public InvoiceDTO(String number,Date sellingDate, Date invoiceDate, double sum, Date confirmDate, long idAddress, long idCustomer) {
        this.number = number;
        this.idAddress = idAddress;
        this.idCustomer = idCustomer;
        this.sellingDate = sellingDate;
        this.sum = sum;
        this.confirmDate = confirmDate;
        this.invoiceDate = invoiceDate;

    }
    private String number;
    private Date sellingDate;
    private Date invoiceDate;
    private double sum;
    private Date confirmDate;

    private Customer customer;
    List<InvoiceItem> invoiceItems;
    private Address address;

    private long idAddress;
    private long idCustomer;

    private AddressDTO address2;
    private CustomerDTO customer2;







}