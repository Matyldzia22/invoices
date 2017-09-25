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

public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDAO addressDAO;

    @Autowired
    private InvoiceDAO invoiceDAO;

    @Autowired
    private MapperFacade mapperFacade;


    @Override
    public void saveAddress(AddressDTO addressDTO){
        Address address = mapperFacade.map(addressDTO, Address.class);
        addressDAO.save(address);
    }

    @Override
    public void updateAddress(AddressDTO addressDTO){
        Address address2 =addressDAO.getById(addressDTO.getId());
        addressDAO.update(address2);
    }

    @Override
    public void deleteAddress(AddressDTO addressDTO){
        Address address3 = addressDAO.getById(addressDTO.getId());
        addressDAO.delete(address3);
    }

    @Override
    public Address getAddressById(Long id){
        return addressDAO.getById(id);
    }

    @Override
    public List<Address> getAddressByCity(String city){
        return addressDAO.getAddressByCity(city);
    }

    @Override
    public List<Address> getAddressByStreet(String street){
        return addressDAO.getAddressByStreet(street);
    }

    @Override
    public List<Address> getAddressByPostCode(String postCode){
        return addressDAO.getAddressByPostCode(postCode);
    }

    @Override
    public  List<AddressDTO> getAllAddresses(){
        List<AddressDTO> addresses = new ArrayList<>();
        addressDAO.getAll().forEach(address -> addresses.add(mapperFacade.map(address, AddressDTO.class)));
        return addresses;
    }

    public void addInvoice2Address(Address address, Invoice invoice){
        if(invoice.getAddress() != address) {
            invoice.setAddress(address);
            invoiceDAO.update(invoice);
        }
        if (!addressDAO.getInvoices(address).contains(invoice)){
            List<Invoice> invoices = addressDAO.getInvoices(address);
            invoices.add(invoice);
            address.setInvoices(invoices);
            addressDAO.update(address);

        }
    }

    @Override
    public void deleteInvoiceFromAddress(Address address, Invoice invoice){
        if(invoice.getAddress() == address){
            invoice.setAddress(null);
            invoiceDAO.update(invoice);

        }
        if(addressDAO.getInvoices(address).contains(invoice)){

            List<Invoice> invoices2 = addressDAO.getInvoices(address);
            invoices2.remove(invoice);
            address.setInvoices(invoices2);
            addressDAO.update(address);
        }
    }

    @Override
    public List<Invoice> getInvoices(Address address){
        return  addressDAO.getInvoices(address);
    }

}