package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;
import com.example.facture.jpa.model.Product;

import java.util.List;

public interface ProductDAO extends BaseDAO<Product, Long> {

    List<Product> getAll();

    Product getProductByName(String name);

    Product getProductByVat(int vat);

    Product getProductByBruttoPrice(double bruttoPrice);

    Product getProductByNettoPrice(double nettoPrice);

    List<InvoiceItem> getInvoiceItems(Product product);

    long getProdId();


}