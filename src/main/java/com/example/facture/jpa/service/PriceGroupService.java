package com.example.facture.jpa.service;

import com.example.facture.jpa.dto.*;
import com.example.facture.jpa.model.*;

import java.util.Date;
import java.util.List;

public interface PriceGroupService {

    List<PriceGroupDTO> getAllPriceGroups();

    PriceGroup getPriceGroupById(Long id);

    void deletePriceGroup(PriceGroupDTO priceGroupDTO);

    void updatePriceGroup(PriceGroupDTO priceGroupDTO);
    PriceGroupDTO getPriceGroupByName(String name);
    PriceGroupDTO getPriceGroupByDiscount(int discount);


    void addCustomer2PriceGroup(PriceGroup priceGroup, Customer customer);
    void deleteCustomerFromPriceGroup(PriceGroup priceGroup, Customer customer);
    List<Customer> getCustomers(PriceGroup priceGroup);


}