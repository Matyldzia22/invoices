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

public class TypeOfAddressServiceImpl implements TypeOfAddressService {


    private TypeOfAddressDAO typeOfAddressDAO;
    private AddressDAO addressDAO;
    private MapperFacade mapperFacade;

    @Autowired
    public TypeOfAddressServiceImpl(TypeOfAddressDAO typeOfAddressDAO, AddressDAO addressDAO, MapperFacade mapperFacade) {
        this.typeOfAddressDAO = typeOfAddressDAO;
        this.addressDAO = addressDAO;
        this.mapperFacade = mapperFacade;
    }


    @Override
    public void saveTypeOfAddress(TypeOfAddressDTO typeOfAddressDTO) {
        TypeOfAddress typeOfAddress = mapperFacade.map(typeOfAddressDTO, TypeOfAddress.class);
        typeOfAddressDAO.save(typeOfAddress);
    }

    @Override
    public void updateTypeOfAddress(TypeOfAddressDTO typeOfAddressDTO) {
        TypeOfAddress typeOfAddress2 = typeOfAddressDAO.getById(typeOfAddressDTO.getId());
        typeOfAddressDAO.update(typeOfAddress2);
    }

    @Override
    public void deleteTypeOfAddress(TypeOfAddressDTO typeOfAddressDTO) {
        TypeOfAddress typeOfAddress = typeOfAddressDAO.getById(typeOfAddressDTO.getId());
        typeOfAddressDAO.delete(typeOfAddress);
    }

    @Override
    public TypeOfAddress getTypeOfAddressById(Long id) {
        return typeOfAddressDAO.getById(id);
    }

    @Override
    public TypeOfAddressDTO getTypeOfAddressByName(String name) {
        return mapperFacade.map(typeOfAddressDAO.getTypeOfAddressByName(name), TypeOfAddressDTO.class);
    }

    @Override
    public List<TypeOfAddressDTO> getAllTypeOfAddresses() {
        List<TypeOfAddressDTO> typeOfAddresses = new ArrayList<>();
        typeOfAddressDAO.getAll().forEach(typeOfAddress -> typeOfAddresses.add(mapperFacade.map(typeOfAddress, TypeOfAddressDTO.class)));
        return typeOfAddresses;
    }

    public void addAddress2TypeOfAddress(TypeOfAddress typeOfAddress, Address address) {
        if (address.getTypeOfAddress() != typeOfAddress) {
            address.setTypeOfAddress(typeOfAddress);
            addressDAO.update(address);
        }
        List<Address> addresses = typeOfAddressDAO.getAddresses(typeOfAddress);
        if (!addresses.contains(address)) {
            addresses.add(address);
            typeOfAddress.setAddresses(addresses);
            typeOfAddressDAO.update(typeOfAddress);

        }
    }

    @Override
    public void deleteAddressFromTypeOfAddress(TypeOfAddress typeOfAddress, Address address) {
        if (address.getTypeOfAddress() == typeOfAddress) {
            address.setTypeOfAddress(null);
            addressDAO.update(address);

        }
        List<Address> addresses = typeOfAddressDAO.getAddresses(typeOfAddress);
        if (addresses.contains(address)) {
            addresses.remove(address);
            typeOfAddress.setAddresses(addresses);
            typeOfAddressDAO.update(typeOfAddress);
        }
    }

    @Override
    public List<Address> getAddresses(TypeOfAddress typeOfAddress) {
        return typeOfAddressDAO.getAddresses(typeOfAddress);
    }

    @Override
    public List<TypeOfAddress> getAllTypeOfAddressess() {
        return typeOfAddressDAO.getAll();
    }

}