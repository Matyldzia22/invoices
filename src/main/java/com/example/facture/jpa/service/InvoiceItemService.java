package com.example.facture.jpa.service;

import com.example.facture.jpa.dto.*;
import com.example.facture.jpa.model.*;

import java.util.Date;
import java.util.List;

public interface InvoiceItemService {

    List<InvoiceItemDTO> getAllInvoiceItems();

    Invoice getInvoiceItemById(Long id);

    void deleteInvoiceItem(InvoiceItemDTO invoiceItemDTO);

    void updateInvoiceItem(InvoiceItemDTO invoiceItemDTO);
    InvoiceItemDTO getInvoiceItemByNumber(int number);




}