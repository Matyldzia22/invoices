package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;


import com.example.facture.jpa.model.InvoiceItem;


import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

public interface InvoiceItemDAO extends BaseDAO<InvoiceItem, Long> {

    InvoiceItem getInvoiceItemByNumber(int number);
    List<InvoiceItem> getInvoiceItemByIdProduct(Long idProduct);
    List<InvoiceItem>  getInvoiceItemByIdInvoice(Long idInvoice);

    List<InvoiceItem> getInvoiceItems(long idProduct, long idInvoice );

















}