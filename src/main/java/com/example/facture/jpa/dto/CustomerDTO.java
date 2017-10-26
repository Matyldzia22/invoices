package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.Invoice;
import com.example.facture.jpa.model.Address;
import com.example.facture.jpa.model.TypeOfCustomer;
import com.example.facture.jpa.model.TaxBracket;
import com.example.facture.jpa.model.PriceGroup;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.valuehandling.UnwrapValidatedValue;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;
import java.util.List;

import static org.apache.taglibs.standard.functions.Functions.substring;

@Data
@NoArgsConstructor
public class CustomerDTO {

    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String email;
    @NotBlank
    private String nip;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String name;
    @Range(min=1, message = "Please select a type of customer!")
    private long typeOfCustomerId;
    @Range(min=1, message = "Please select a price group!")
    private long priceGroupId;
    @Range(min=1, message = "Please select a tax bracket!")
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