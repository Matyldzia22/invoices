package com.example.facture.jpa.service;

import com.example.facture.jpa.dao.*;
import com.example.facture.jpa.dto.*;
import com.example.facture.jpa.model.*;
import com.example.facture.jpa.service.Mapper;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional

public class TaxBracketServiceImpl implements TaxBracketService {

    @Autowired
    private TaxBracketDAO taxBracketDAO;

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private MapperFacade mapperFacade;

    @Override
    public void saveTaxBracket(TaxBracketDTO taxBracketDTO) {
        TaxBracket taxBracket = mapperFacade.map(taxBracketDTO, TaxBracket.class);
        taxBracketDAO.save(taxBracket);
    }

    @Override
    public void updateTaxBracket(TaxBracketDTO taxBracketDTO) {
        TaxBracket taxBracket2 = taxBracketDAO.getById(taxBracketDTO.getId());
        taxBracketDAO.update(taxBracket2);
    }

    @Override
    public void deleteTaxBracket(TaxBracketDTO taxBracketDTO) {
        TaxBracket taxBracket = taxBracketDAO.getById(taxBracketDTO.getId());
        taxBracketDAO.delete(taxBracket);
    }

    @Override
    public TaxBracket getTaxBracketById(Long id) {
        return taxBracketDAO.getById(id);
    }

    @Override
    public TaxBracketDTO getTaxBracketByNumber(int number) {
        return mapperFacade.map(taxBracketDAO.getTaxBracketByNumber(number), TaxBracketDTO.class);
    }

    @Override
    public List<TaxBracketDTO> getAllTaxBrackets() {
        List<TaxBracketDTO> taxBrackets = new ArrayList<>();
        taxBracketDAO.getAll().forEach(taxBracket -> taxBrackets.add(mapperFacade.map(taxBracket, TaxBracketDTO.class)));
        return taxBrackets;
    }

    @Override
    public void addCustomer2TaxBracket(TaxBracket taxBracket, Customer customer) {
        if (customer.getTaxBracket() != taxBracket) {
            customer.setTaxBracket(taxBracket);
            customerDAO.update(customer);
        }
        List<Customer> customers = taxBracketDAO.getCustomers(taxBracket);
        if (!customers.contains(customer)) {
            customers.add(customer);
            taxBracket.setCustomers(customers);
            taxBracketDAO.update(taxBracket);

        }
    }

    @Override
    public void deleteCustomerFromTaxBracket(TaxBracket taxBracket, Customer customer) {
        if (customer.getTaxBracket() == taxBracket) {
            customer.setTaxBracket(null);
            customerDAO.update(customer);

        }
        List<Customer> customers = taxBracketDAO.getCustomers(taxBracket);
        if (customers.contains(customer)) {
            customers.remove(customer);
            taxBracket.setCustomers(customers);
            taxBracketDAO.update(taxBracket);
        }
    }

    @Override
    public List<Customer> getCustomers(TaxBracket taxBracket) {
        return taxBracketDAO.getCustomers(taxBracket);
    }

    @Override
    public List<TaxBracket> getAllTaxBracketss() {
        return taxBracketDAO.getAll();
    }


}