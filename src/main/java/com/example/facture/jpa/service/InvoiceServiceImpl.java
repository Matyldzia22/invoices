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

public class InvoiceServiceImpl implements InvoiceService {


    private InvoiceDAO invoiceDAO;
    private InvoiceItemDAO invoiceItemDAO;
    private AddressDAO addressDAO;
    private CustomerDAO customerDAO;
    private MapperFacade mapperFacade;

    @Autowired
    public InvoiceServiceImpl(InvoiceDAO invoiceDAO, InvoiceItemDAO invoiceItemDAO, AddressDAO addressDAO, CustomerDAO customerDAO, MapperFacade mapperFacade) {
        this.invoiceDAO = invoiceDAO;
        this.invoiceItemDAO = invoiceItemDAO;
        this.addressDAO = addressDAO;
        this.customerDAO = customerDAO;
        this.mapperFacade = mapperFacade;
    }


    @Override
    public void saveInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice = mapperFacade.map(invoiceDTO, Invoice.class);
        addAddress2Invoice(invoice, addressDAO.getById(invoiceDTO.getAddressId()));
        addCustomer2Invoice(invoice, customerDAO.getById(invoiceDTO.getCustomerId()));
        //addInvoiceItem2Invoice(invoice, invoiceItemDAO.getById(invoiceDTO.getInvoiceItemId()));

        invoiceDAO.save(invoice);
    }

    @Override
    public void updateInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice2 = invoiceDAO.getById(invoiceDTO.getId());
        invoiceDAO.update(invoice2);
    }

    @Override
    public void updateFrom(InvoiceDTO invoiceDTO) {
        Invoice invoice2 = invoiceDAO.getById(invoiceDTO.getId());
        invoice2.setConfirmDate(invoiceDAO.getConfirmDate(invoiceDTO.getId()));

        invoiceDAO.update(invoice2);
    }

    @Override
    public void updateInvoiceFrom(InvoiceDTO invoiceDTO) {
        Invoice inv = invoiceDAO.getById(invoiceDTO.getId());
        inv.setSum(invoiceDAO.getSum(invoiceDTO.getId()));

        invoiceDAO.update(inv);
    }

    @Override
    public void updateInvoiceFromNumber(InvoiceDTO invoiceDTO) {
        Invoice inv = invoiceDAO.getInvoiceByNumber(invoiceDTO.getNumberr());
        inv.setSum(invoiceDAO.getSuma(invoiceDTO.getNumberr()));

        invoiceDAO.update(inv);
    }


    @Override
    public void deleteInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice3 = invoiceDAO.getById(invoiceDTO.getId());
        invoiceDAO.delete(invoice3);
    }

    @Override
    public Invoice getInvoiceById(Long id) {
        return invoiceDAO.getById(id);
    }

    @Override
    public Invoice getInvoiceByNumberrr(String number) {
        return invoiceDAO.getInvoiceByNumber(number);
    }


    @Override
    public List<Invoice> getInvoiceBySellingDate(Date sellingDate) {
        return invoiceDAO.getInvoiceBySellingDate(sellingDate);
    }

    @Override
    public List<Invoice> getInvoiceByConfirmDate(Date confirmDate) {
        return invoiceDAO.getInvoiceByConfirmDate(confirmDate);
    }

    @Override
    public List<Invoice> getInvoiceByInvoiceDate(Date invoiceDate) {
        return invoiceDAO.getInvoiceByInvoiceDate(invoiceDate);
    }

    @Override
    public List<Invoice> getInvoiceByNumberr(String number) {
        return invoiceDAO.getInvoiceByNumberr(number);
    }

    @Override
    public InvoiceDTO getInvoiceByNumber(String number) {
        return mapperFacade.map(invoiceDAO.getInvoiceByNumber(number), InvoiceDTO.class);
    }

    @Override
    public InvoiceDisplayDTO getInvoiceDisplayByNumber(String number) {
        return mapperFacade.map(invoiceDAO.getInvoiceByNumber(number), InvoiceDisplayDTO.class);
    }


    @Override
    public List<InvoiceDTO> getAllInvoices() {
        List<InvoiceDTO> invoices = new ArrayList<>();
        invoiceDAO.getAll().forEach(invoice -> invoices.add(mapperFacade.map(invoice, InvoiceDTO.class)));
        return invoices;
    }

    @Override
    public List<InvoiceItemDTO> getAllInvoiceItemss() {
        List<InvoiceItemDTO> invoiceItemss = new ArrayList<>();
        invoiceItemDAO.getAll().forEach(invoiceItem -> invoiceItemss.add(mapperFacade.map(invoiceItem, InvoiceItemDTO.class)));
        return invoiceItemss;
    }

    public void addInvoiceItem2Invoice(Invoice invoice, InvoiceItem invoiceItem) {
        if (invoiceItem.getInvoice() != invoice) {
            invoiceItem.setInvoice(invoice);
            invoiceItemDAO.update(invoiceItem);
        }
        if (!invoiceDAO.getInvoiceItems(invoice).contains(invoiceItem)) {
            List<InvoiceItem> invoiceItems = invoiceDAO.getInvoiceItems(invoice);
            invoiceItems.add(invoiceItem);
            invoice.setInvoiceItems(invoiceItems);
            invoiceDAO.update(invoice);

        }
    }

    @Override
    public void deleteInvoiceItemFromInvoice(Invoice invoice, InvoiceItem invoiceItem) {
        if (invoiceItem.getInvoice() == invoice) {
            invoiceItem.setInvoice(null);
            invoiceItemDAO.update(invoiceItem);

        }
        if (invoiceDAO.getInvoiceItems(invoice).contains(invoiceItem)) {

            List<InvoiceItem> invoiceItems2 = invoiceDAO.getInvoiceItems(invoice);
            invoiceItems2.remove(invoiceItem);
            invoice.setInvoiceItems(invoiceItems2);
            invoiceDAO.update(invoice);
        }
    }

    @Override
    public List<InvoiceItem> getInvoiceItems(Invoice invoice) {
        return invoiceDAO.getInvoiceItems(invoice);
    }


    @Override
    public List<Invoice> getInvoiceByaddressId(Long addressId) {
        return invoiceDAO.getInvoiceByaddressId(addressId);
    }

    @Override
    public List<Invoice> getInvoiceBycustomerId(Long customerId) {
        return invoiceDAO.getInvoiceBycustomerId(customerId);
    }

    @Override
    public List<Invoice> getInvoices(long addressId, long customerId) {
        return invoiceDAO.getInvoices(addressId, customerId);
    }

    @Override
    public void addAddress2Invoice(Invoice invoice, Address address) {
        if (invoice.getAddress() != address) {
            invoice.setAddress(address);
            invoiceDAO.update(invoice);
        }
        List<Invoice> invoices = addressDAO.getInvoices(address);
        if (!invoices.contains(invoice)) {
            invoices.add(invoice);
            address.setInvoices(invoices);
            addressDAO.update(address);
        }
    }

    @Override
    public void deleteAddressFromInvoice(Invoice invoice, Address address) {
        if (invoice.getAddress() == address) {
            invoice.setAddress(null);
            invoiceDAO.update(invoice);
        }
        List<Invoice> invoices = addressDAO.getInvoices(address);
        if (invoices.contains(invoice)) {
            invoices.remove(invoice);
            address.setInvoices(invoices);
            addressDAO.update(address);
        }
    }

    @Override
    public void addCustomer2Invoice(Invoice invoice, Customer customer) {
        if (invoice.getCustomer() != customer) {
            invoice.setCustomer(customer);
            invoiceDAO.update(invoice);
        }
        List<Invoice> invoices = customerDAO.getInvoices(customer);
        if (!invoices.contains(invoice)) {
            invoices.add(invoice);
            customer.setInvoices(invoices);
            customerDAO.update(customer);
        }
    }

    @Override
    public void deleteCustomerFromInvoice(Invoice invoice, Customer customer) {
        if (invoice.getCustomer() == customer) {
            invoice.setCustomer(null);
            invoiceDAO.update(invoice);
        }
        List<Invoice> invoices = customerDAO.getInvoices(customer);
        if (invoices.contains(invoice)) {
            invoices.remove(invoice);
            customer.setInvoices(invoices);
            customerDAO.update(customer);
        }
    }

    @Override
    public List<Invoice> getAllInvoicess() {
        return invoiceDAO.getAll();
    }

    @Override
    public long getInId() {
        return invoiceDAO.getInId();
    }


    @Override
    public double getSum(Long id) {
        return invoiceDAO.getSum(id);
    }

    @Override
    public double getInvoiceItemsSum(Long id) {
        return invoiceDAO.getInvoiceItemsSum(id);
    }

    @Override
    public double getInvoiceItemsSumm(String numberr) {
        return invoiceDAO.getInvoiceItemsSumm(numberr);
    }

    @Override
    public double getSuma(String numberr) {
        return invoiceDAO.getSuma(numberr);
    }


}