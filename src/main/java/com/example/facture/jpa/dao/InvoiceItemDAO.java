package com.example.facture.jpa.dao;

import com.example.facture.jpa.model.*;


import com.example.facture.jpa.model.InvoiceItem;


import java.util.Date;
import java.util.List;

public interface InvoiceItemDAO extends BaseDAO<InvoiceItem, Long> {

    InvoiceItem getInvoiceItemByNumber(int number);













}