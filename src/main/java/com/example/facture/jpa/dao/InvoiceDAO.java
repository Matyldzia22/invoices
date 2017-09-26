package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;


import com.example.facture.jpa.model.Invoice;


import java.util.Date;
import java.util.List;

public interface InvoiceDAO extends BaseDAO<Invoice, Long> {

    Invoice getInvoiceByNumber(String number);


    List<Invoice> getInvoiceByInvoiceDate(Date invoiceDate);
    List<Invoice> getInvoiceBySellingDate(Date sellingDate);
    List<Invoice> getInvoiceByConfirmDate(Date confirmDate);

    List<InvoiceItem> getInvoiceItems(Invoice invoice);

    List<Invoice> getInvoiceByIdAddress(Long idAddress);
    List<Invoice> getInvoiceByIdCustomer(Long idCustomer);

    List<Invoice> getInvoices(long idAddress, long idCustomer );












}