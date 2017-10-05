package com.example.facture.jpa.service;

import com.example.facture.jpa.dto.*;
import com.example.facture.jpa.model.*;


import java.util.List;

public interface TaxBracketService {

    List<TaxBracket> getAllTaxBracketss();

    List<TaxBracketDTO> getAllTaxBrackets();

    TaxBracket getTaxBracketById(Long id);

    void saveTaxBracket(TaxBracketDTO taxBracketDTO);

    void deleteTaxBracket(TaxBracketDTO taxBracketDTO);

    void updateTaxBracket(TaxBracketDTO taxBracketDTO);

    TaxBracketDTO getTaxBracketByNumber(int number);

    void addCustomer2TaxBracket(TaxBracket taxBracket, Customer customer);

    void deleteCustomerFromTaxBracket(TaxBracket taxBracket, Customer customer);

    List<Customer> getCustomers(TaxBracket taxBracket);
}