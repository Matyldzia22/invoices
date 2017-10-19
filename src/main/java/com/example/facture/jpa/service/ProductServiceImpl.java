package com.example.facture.jpa.service;

import com.example.facture.jpa.dao.*;
import com.example.facture.jpa.dto.*;
import com.example.facture.jpa.model.*;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private InvoiceItemDAO invoiceItemDAO;

    @Autowired
    private MapperFacade mapperFacade;


    @Override
    public void saveProduct(ProductDTO productDTO) {

        Product product = mapperFacade.map(productDTO, Product.class);
        productDAO.save(product);

    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> products = new ArrayList<>();
        productDAO.getAll().forEach(product -> products.add(mapperFacade.map(product, ProductDTO.class)));
        return products;
    }

    @Override
    public Product getProductById(Long id) {
        return productDAO.getById(id);
    }

    @Override
    public void deleteProduct(ProductDTO productDTO) {
        Product product = productDAO.getById(productDTO.getId());
        productDAO.delete(product);
    }

    @Override
    public void updateProduct(ProductDTO productDTO) {
        Product product2 = productDAO.getById(productDTO.getId());
        productDAO.update(product2);
    }

    @Override
    public void updateProductAuto(ProductDTO productDTO) {
        Product prod = productDAO.getById(productDTO.getId());
        prod.setNettoPrice(productDAO.getNettoPrice(productDTO.getId()));

        productDAO.update(prod);
    }

    @Override
    public ProductDTO getProductByName(String name) {
        return mapperFacade.map(productDAO.getProductByName(name), ProductDTO.class);
    }

    @Override
    public ProductDTO getProductByVat(int vat) {
        return mapperFacade.map(productDAO.getProductByVat(vat), ProductDTO.class);
    }

    @Override
    public ProductDTO getProductByBruttoPrice(double bruttoPrice) {
        return mapperFacade.map(productDAO.getProductByBruttoPrice(bruttoPrice), ProductDTO.class);
    }

    @Override
    public ProductDTO getProductByNettoPrice(double nettoPrice) {
        return mapperFacade.map(productDAO.getProductByNettoPrice(nettoPrice), ProductDTO.class);
    }

    @Override
    public void addInvoiceItem2Product(Product product, InvoiceItem invoiceItem) {
        if (invoiceItem.getProduct() != product) {
            invoiceItem.setProduct(product);
            invoiceItemDAO.update(invoiceItem);
        }
        List<InvoiceItem> invoiceItems = productDAO.getInvoiceItems(product);
        if (!invoiceItems.contains(invoiceItem)) {
            invoiceItems.add(invoiceItem);
            product.setInvoiceItems(invoiceItems);
            productDAO.update(product);
        }
    }

    @Override
    public void deleteInvoiceItemFromProduct(Product product, InvoiceItem invoiceItem) {
        if (invoiceItem.getProduct() == product) {
            invoiceItem.setProduct(null);
            invoiceItemDAO.update(invoiceItem);
        }
        List<InvoiceItem> invoiceItems = productDAO.getInvoiceItems(product);
        if (invoiceItems.contains(invoiceItem)) {
            invoiceItems.remove(invoiceItem);
            product.setInvoiceItems(invoiceItems);
            productDAO.update(product);
        }
    }

    public List<InvoiceItem> getInvoiceItems(Product product) {
        return productDAO.getInvoiceItems(product);
    }

    @Override
    public List<Product> getAllProductss() {
        return productDAO.getAll();
    }

    @Override
    public long getProdId() {
        return productDAO.getProdId();
    }

    @Override
    public double getNettoPrice(Long id) {
        return productDAO.getNettoPrice(id);
    }


}