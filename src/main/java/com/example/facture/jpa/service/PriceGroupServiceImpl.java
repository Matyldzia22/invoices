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

public class PriceGroupServiceImpl implements PriceGroupService {

    @Autowired
    private PriceGroupDAO priceGroupDAO;

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private MapperFacade mapperFacade;


    @Override
    public void savePriceGroup(PriceGroupDTO priceGroupDTO) {

        PriceGroup priceGroup = mapperFacade.map(priceGroupDTO, PriceGroup.class);
        priceGroupDAO.save(priceGroup);
    }

    @Override
    public void deletePriceGroup(PriceGroupDTO priceGroupDTO) {
        PriceGroup priceGroup = priceGroupDAO.getById(priceGroupDTO.getId());
        priceGroupDAO.delete(priceGroup);
    }

    @Override
    public void updatePriceGroup(PriceGroupDTO priceGroupDTO) {
        PriceGroup priceGroup2 = priceGroupDAO.getById(priceGroupDTO.getId());
        priceGroupDAO.update(priceGroup2);
    }

    @Override
    public List<PriceGroupDTO> getAllPriceGroups() {
        List<PriceGroupDTO> priceGroups = new ArrayList<>();
        priceGroupDAO.getAll().forEach(priceGroup -> priceGroups.add(mapperFacade.map(priceGroup, PriceGroupDTO.class)));
        return priceGroups;
    }

    @Override
    public PriceGroup getPriceGroupById(Long id) {
        return priceGroupDAO.getById(id);
    }

    @Override
    public PriceGroupDTO getPriceGroupByName(String name) {
        return mapperFacade.map(priceGroupDAO.getPriceGroupByName(name), PriceGroupDTO.class);
    }

    @Override
    public PriceGroupDTO getPriceGroupByDiscount(int discount) {
        return mapperFacade.map(priceGroupDAO.getPriceGroupByDiscount(discount), PriceGroupDTO.class);
    }

    @Override
    public void addCustomer2PriceGroup(PriceGroup priceGroup, Customer customer) {
        if (customer.getPriceGroup() != priceGroup) {
            customer.setPriceGroup(priceGroup);
            customerDAO.update(customer);
        }
        List<Customer> customers = priceGroupDAO.getCustomers(priceGroup);
        if (!customers.contains(customer)) {
            customers.add(customer);
            priceGroup.setCustomers(customers);
            priceGroupDAO.update(priceGroup);
        }
    }

    @Override
    public void deleteCustomerFromPriceGroup(PriceGroup priceGroup, Customer customer) {
        if (customer.getPriceGroup() == priceGroup) {
            customer.setPriceGroup(null);
            customerDAO.update(customer);
        }
        List<Customer> customers2 = priceGroupDAO.getCustomers(priceGroup);
        if (customers2.contains(customer)) {
            customers2.remove(customer);
            priceGroup.setCustomers(customers2);
            priceGroupDAO.update(priceGroup);
        }
    }

    @Override
    public List<Customer> getCustomers(PriceGroup priceGroup) {
        return priceGroupDAO.getCustomers(priceGroup);
    }

    @Override
    public List<PriceGroup> getAllPriceGroupss() {
        return priceGroupDAO.getAll();
    }


}