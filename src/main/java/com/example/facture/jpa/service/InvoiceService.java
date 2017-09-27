package com.example.facture.jpa.service;

import com.example.facture.jpa.dto.*;
import com.example.facture.jpa.model.*;

import java.util.Date;
import java.util.List;

public interface InvoiceService {

    void saveInvoice(InvoiceDTO invoiceDTO);

    List<InvoiceDTO> getAllInvoices();

    Invoice getInvoiceById(Long id);

    void deleteInvoice(InvoiceDTO invoiceDTO);

    void updateInvoice(InvoiceDTO invoiceDTO);
    InvoiceDTO getInvoiceByNumber(String number);
    InvoiceDisplayDTO getInvoiceDisplayByNumber(String number);

    List<Invoice> getInvoiceByInvoiceDate(Date invoiceDate);
    List<Invoice> getInvoiceBySellingDate(Date sellingDate);
    List<Invoice> getInvoiceByConfirmDate(Date confirmDate);


    void addInvoiceItem2Invoice(Invoice invoice, InvoiceItem invoiceItem);
    void deleteInvoiceItemFromInvoice(Invoice invoice, InvoiceItem invoiceItem);
    void addAddress2Invoice(Invoice invoice, Address address);
    void addCustomer2Invoice(Invoice invoice, Customer customer);
    void deleteAddressFromInvoice(Invoice invoice, Address address);
    void deleteCustomerFromInvoice(Invoice invoice, Customer customer);

    List<Invoice> getInvoiceByIdAddress(Long idAddress);
    List<Invoice> getInvoiceByIdCustomer(Long idCustomer);
    List<Invoice> getInvoices(long idAddress, long idCustomer);

    List<InvoiceItem> getInvoiceItems(Invoice invoice);


}