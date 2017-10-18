package com.example.facture.jpa.service;

import com.example.facture.jpa.dto.*;
import com.example.facture.jpa.model.*;

import java.util.Date;
import java.util.List;

public interface InvoiceService {

    List<Invoice> getAllInvoicess();

    void saveInvoice(InvoiceDTO invoiceDTO);

    List<InvoiceDTO> getAllInvoices();

    List<InvoiceItemDTO> getAllInvoiceItemss();

    Invoice getInvoiceById(Long id);

    void deleteInvoice(InvoiceDTO invoiceDTO);

    void updateInvoice(InvoiceDTO invoiceDTO);

    InvoiceDTO getInvoiceByNumber(String number);

    Invoice getInvoiceByNumberrr(String number);

    InvoiceDisplayDTO getInvoiceDisplayByNumber(String number);

    List<Invoice> getInvoiceByInvoiceDate(Date invoiceDate);

    List<Invoice> getInvoiceBySellingDate(Date sellingDate);

    List<Invoice> getInvoiceByConfirmDate(Date confirmDate);

    List<Invoice> getInvoiceByNumberr(String number);

    void addInvoiceItem2Invoice(Invoice invoice, InvoiceItem invoiceItem);

    void deleteInvoiceItemFromInvoice(Invoice invoice, InvoiceItem invoiceItem);

    void addAddress2Invoice(Invoice invoice, Address address);

    void addCustomer2Invoice(Invoice invoice, Customer customer);

    void deleteAddressFromInvoice(Invoice invoice, Address address);

    void deleteCustomerFromInvoice(Invoice invoice, Customer customer);

    List<Invoice> getInvoiceByaddressId(Long addressId);

    List<Invoice> getInvoiceBycustomerId(Long customerId);

    List<Invoice> getInvoices(long addressId, long customerId);

    List<InvoiceItem> getInvoiceItems(Invoice invoice);

    long getInId();


}