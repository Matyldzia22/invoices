package com.example.facture.jpa.service;

import com.example.facture.jpa.dto.*;
import com.example.facture.jpa.model.*;

import java.util.Date;
import java.util.List;

public interface InvoiceItemService {

    void saveInvoiceItem(InvoiceItemDTO invoiceItemDTO);

    List<InvoiceItem> getAllInvoiceItems();

    InvoiceItem getInvoiceItemById(Long id);

    void deleteInvoiceItem(InvoiceItem invoiceItem);

    void updateInvoiceItem(InvoiceItem invoiceItem);

    void addProduct2InvoiceItem(InvoiceItem invoiceItem, Product product);
    void addInvoice2InvoiceItem(InvoiceItem invoiceItem, Invoice invoice);
    void deleteInvoiceFromInvoiceItem(InvoiceItem invoiceItem, Invoice invoice);
    void deleteProductFromInvoiceItem(InvoiceItem invoiceItem, Product product);




}