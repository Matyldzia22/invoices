package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;


import com.example.facture.jpa.model.Product;


import java.util.List;

public interface ProductDAO extends BaseDAO<Product, Long> {

    Product getProductByName(String name);









}