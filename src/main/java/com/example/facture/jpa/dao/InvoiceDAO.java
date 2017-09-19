package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;


import com.example.facture.jpa.model.Invoice;


import java.util.Date;
import java.util.List;

public interface InvoiceDAO extends BaseDAO<Invoice, Long> {

    Invoice getInvoiceByNumber(String number);
    Invoice getInvoiceBySellingDate(Date sellingDate);
    Invoice getInvoiceByInvoiceDate(Date invoiceDate);
    Invoice getInvoiceBySum(double sum);
    Invoice getInvoiceByConfirmDate(Date confirmDate);

    List<InvoiceItem> getInvoiceItems(Invoice invoice);










}