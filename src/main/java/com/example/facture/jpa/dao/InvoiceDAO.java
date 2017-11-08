package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;


import com.example.facture.jpa.model.Invoice;


import java.util.Date;
import java.util.List;

public interface InvoiceDAO extends BaseDAO<Invoice, Long> {

    Invoice getInvoiceByNumber(String number);

    List<Invoice> getAll();

    List<Invoice> getInvoiceByInvoiceDate(Date invoiceDate);

    List<Invoice> getInvoiceBySellingDate(Date sellingDate);

    List<Invoice> getInvoiceByConfirmDate(Date confirmDate);

    List<Invoice> getInvoiceByNumberr(String number);

    List<InvoiceItem> getInvoiceItems(Invoice invoice);

    List<Invoice> getInvoiceByaddressId(Long addressId);

    List<Invoice> getInvoiceBycustomerId(Long customerId);

    List<Invoice> getInvoices(long addressId, long customerId);

    long getInId();

    double getSum(long id);

    double getInvoiceItemsSum(long id);

    double getInvoiceItemsSumm(String numberr);

    double getSuma(String numberr);

    void updateFrom(Invoice invoice);

    Date getConfirmDate(long id);

}