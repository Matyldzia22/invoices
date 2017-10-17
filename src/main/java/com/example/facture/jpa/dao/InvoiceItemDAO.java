package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;
import com.example.facture.jpa.model.InvoiceItem;

import java.util.Date;
import java.util.List;

public interface InvoiceItemDAO extends BaseDAO<InvoiceItem, Long> {

    List<InvoiceItem> getAll();

    InvoiceItem getInvoiceItemByNumber(int number);

    List<InvoiceItem> getInvoiceItemByproductId(Long productId);

    List<InvoiceItem> getInvoiceItemByinvoiceId(Long invoiceId);

    List<InvoiceItem> getInvoiceItems(long productId, long invoiceId);

    long getInvId();


}