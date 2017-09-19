package com.example.facture.jpa.service;

import com.example.facture.jpa.dto.*;
import com.example.facture.jpa.model.*;

import java.util.Date;
import java.util.List;

public interface InvoiceService {

    List<InvoiceDTO> getAllInvoices();

    Invoice getInvoiceById(Long id);

    void deleteInvoice(InvoiceDTO invoiceDTO);

    void updateInvoice(InvoiceDTO invoiceDTO);
    InvoiceDTO getInvoiceByNumber(String name);
    InvoiceDTO getInvoiceByInvoiceDate(Date invoiceDate);
    InvoiceDTO getInvoiceBySellingDate(Date sellingDate);
    InvoiceDTO getInvoiceByConfirmDate(Date confirmDate);

    void addInvoiceItem2Invoice(Invoice invoice, InvoiceItem invoiceItem);
    void deleteInvoiceItemFromInvoice(Invoice invoice, InvoiceItem invoiceItem);
    List<InvoiceItem> getInvoiceItems(Invoice invoice);


}