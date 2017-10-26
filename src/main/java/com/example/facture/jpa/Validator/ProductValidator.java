package com.example.facture.jpa.Validator;

import com.example.facture.jpa.dto.ProductDTO;
import com.example.facture.jpa.model.Customer;
import com.example.facture.jpa.model.Product;
import com.example.facture.jpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;


@Component
public class ProductValidator implements Validator {


    private ProductService productService;

    @Autowired
    public ProductValidator(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public boolean supports(Class clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductDTO productDTO = (ProductDTO) target;
        List<ProductDTO> productList = productService.getAllProducts();
        for (ProductDTO productt : productList) {
            if (productt.getName().equals(productDTO.getName())) {
                errors.rejectValue("name", "", "Name of product already exist");
            }

        }

    }
}
