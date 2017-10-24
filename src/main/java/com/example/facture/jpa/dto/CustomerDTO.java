package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.Invoice;
import com.example.facture.jpa.model.Address;
import com.example.facture.jpa.model.TypeOfCustomer;
import com.example.facture.jpa.model.TaxBracket;
import com.example.facture.jpa.model.PriceGroup;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.List;

import static org.apache.taglibs.standard.functions.Functions.substring;

@Data
@NoArgsConstructor
public class CustomerDTO {

    private Long id;
    @Pattern(regexp = "[A-Za-z]+", message = "FirstName is incorrect. Use only letters!")
    private String firstName;
    @Pattern(regexp = "[A-Za-z]+", message = "LastName is incorrect. Use only letters!")
    private String lastName;
    private String email;
    private String nip;
    private String phoneNumber;
    private String name;
    private long typeOfCustomerId;
    private long priceGroupId;
    private long taxBracketId;
    private PriceGroup priceGroup;
    private TaxBracket taxBracket;
    private TypeOfCustomer typeOfCustomer;
    private PriceGroupDTO priceGroup2;
    private TaxBracketDTO taxBracket2;
    private TypeOfCustomerDTO typeOfCustomer2;
    private List<Invoice> invoices;
    private List<Address> addresses;

    @Override
    public String toString() {
        return String.format("CustomerDTO[%d]", id);
    }


}