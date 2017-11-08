package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.Customer;
import com.example.facture.jpa.model.Invoice;

import com.example.facture.jpa.model.TypeOfAddress;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AddressDTO {

    private Long id;
    @NotBlank
    private String street;
    @NotBlank
    private String number;
    @NotBlank
    private String city;
    @NotBlank
    private String postCode;
    @Range(min = 1, message = "Please select a type of address!")
    private long typeOfAddressId;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dataFrom;


    private Date dataTo;

    private long customerId;
    private List<Invoice> invoices;
    private Customer customer;
    private TypeOfAddress typeOfAddress;
    private TypeOfAddressDTO typeOfAddress2;

}
