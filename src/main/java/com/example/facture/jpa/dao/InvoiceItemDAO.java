package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;
import com.example.facture.jpa.model.InvoiceItem;

import java.util.Date;
import java.util.List;

public interface InvoiceItemDAO extends BaseDAO<InvoiceItem, Long> {

    List<InvoiceItem> getAll();
    InvoiceItem getInvoiceItemByNumber(int number);

    List<InvoiceItem> getInvoiceItemByIdProduct(Long idProduct);

    List<InvoiceItem> getInvoiceItemByIdInvoice(Long idInvoice);

    List<InvoiceItem> getInvoiceItems(long idProduct, long idInvoice);






}