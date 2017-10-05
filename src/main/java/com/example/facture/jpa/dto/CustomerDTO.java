package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.Invoice;
import com.example.facture.jpa.model.Address;
import com.example.facture.jpa.model.TypeOfCustomer;
import com.example.facture.jpa.model.TaxBracket;
import com.example.facture.jpa.model.PriceGroup;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CustomerDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String nip;
    private String phoneNumber;
    private String name;
    private PriceGroup priceGroup;
    private TaxBracket taxBracket;
    private TypeOfCustomer typeOfCustomer;
    private PriceGroupDTO priceGroup2;
    private TaxBracketDTO taxBracket2;
    private TypeOfCustomerDTO typeOfCustomer2;
    private List<Invoice> invoices;
    private List<Address> addresses;
    private long idTypeOfCustomer;
    private long idPriceGroup;
    private long idTaxBracket;




}