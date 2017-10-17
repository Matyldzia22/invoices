package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.Customer;
import com.example.facture.jpa.model.InvoiceItem;
import com.example.facture.jpa.model.Address;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data

public class InvoiceDTO {

    private Long id;


    private String numberr;
    private Date sellingDate;
    private Date invoiceDate;
    private double sum;
    private Date confirmDate;

    private Customer customer;
    List<InvoiceItem> invoiceItems;
    private Address address;

    private long addressId;
    private long customerId;

    private AddressDTO address2;
    private CustomerDTO customer2;
    private PriceGroupDTO priceGroup2;

    private ProductDTO product2;
    private long invoiceItemId;
    private long productId;
    //private long invoiceId;
    private InvoiceItemDTO invoiceItem;

}