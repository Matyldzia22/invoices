package com.example.facture.jpa.service;

import com.example.facture.jpa.dto.*;
import com.example.facture.jpa.model.*;

import java.util.Date;
import java.util.List;

public interface ProductService {

    List<Product> getAllProductss();

    void saveProduct(ProductDTO productDTO);

    List<ProductDTO> getAllProducts();

    Product getProductById(Long id);

    void deleteProduct(ProductDTO productDTO);

    void updateProduct(ProductDTO productDTO);

    ProductDTO getProductByName(String name);

    ProductDTO getProductByVat(int vat);

    ProductDTO getProductByNettoPrice(double nettoPrice);

    ProductDTO getProductByBruttoPrice(double bruttoPrice);

    void addInvoiceItem2Product(Product product, InvoiceItem invoiceItem);

    void deleteInvoiceItemFromProduct(Product product, InvoiceItem invoiceItem);

    List<InvoiceItem> getInvoiceItems(Product product);

    long getProdId();

    double getNettoPrice(Long id);

    void updateProductAuto(ProductDTO productDTO);


}