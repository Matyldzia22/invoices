package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;
import com.example.facture.jpa.model.TaxBracket;
import java.util.List;


public interface TaxBracketDAO extends BaseDAO<TaxBracket, Long> {

    TaxBracket getTaxBracketByNumber(int number);
    List<Customer> getCustomers(TaxBracket taxBracket);










}