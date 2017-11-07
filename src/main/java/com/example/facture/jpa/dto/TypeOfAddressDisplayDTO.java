package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.Address;
import lombok.Data;

import java.util.List;


public @Data
class TypeOfAddressDisplayDTO {

    private Long id;
    private String name;
    private List<Address> addresses;

    @Override
    public String toString() {
        return String.format("TypeOfAddressDTO[%d_%s]", id, name);
    }

}