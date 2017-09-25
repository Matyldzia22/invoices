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
    InvoiceDTO getInvoiceByNumber(String name);

    List<Invoice> getInvoiceByInvoiceDate(Date invoiceDate);
    List<Invoice> getInvoiceBySellingDate(Date sellingDate);
    List<Invoice> getInvoiceByConfirmDate(Date confirmDate);


    void addInvoiceItem2Invoice(Invoice invoice, InvoiceItem invoiceItem);
    void deleteInvoiceItemFromInvoice(Invoice invoice, InvoiceItem invoiceItem);
    List<InvoiceItem> getInvoiceItems(Invoice invoice);


}