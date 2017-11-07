package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.Customer;
import com.example.facture.jpa.model.InvoiceItem;
import com.example.facture.jpa.model.Address;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data

public class InvoiceDTO {

    private Long id;
    @NotBlank
    private String numberr;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date sellingDate;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date invoiceDate;
    @NotNull
    private double sum;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date confirmDate;
    private Customer customer;
    private Address address;
    @Range(min = 1, message = "Please select address!")
    private long addressId;
    private long customerId;
    private AddressDTO address2;
    private CustomerDTO customer2;
    private PriceGroupDTO priceGroup2;
    private ProductDTO product2;
    private long invoiceItemId;
    private long productId;
    private InvoiceItemDTO invoiceItem;
    List<InvoiceItem> invoiceItems;

}