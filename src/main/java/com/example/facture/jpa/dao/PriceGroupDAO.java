package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;


import com.example.facture.jpa.model.PriceGroup;


import java.util.List;

public interface PriceGroupDAO extends BaseDAO<PriceGroup, Long> {

    PriceGroup getPriceGroupByName(String name);
    List<Customer> getCustomers(PriceGroup priceGroup);












}