package com.example.facture.jpa.service;

import com.example.facture.jpa.dao.*;
import com.example.facture.jpa.dto.*;
import com.example.facture.jpa.model.*;
import com.example.facture.jpa.service.Mapper;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@Transactional

public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceDAO invoiceDAO;

    @Autowired
    private InvoiceItemDAO invoiceItemDAO;

    @Autowired
    private MapperFacade mapperFacade;


    @Override
    public void saveInvoice(InvoiceDTO invoiceDTO){
        Invoice invoice = mapperFacade.map(invoiceDTO, Invoice.class);
        invoiceDAO.save(invoice);
    }

    @Override
    public void updateInvoice(InvoiceDTO invoiceDTO){
        Invoice invoice2 =invoiceDAO.getById(invoiceDTO.getId());
        invoiceDAO.update(invoice2);
    }

    @Override
    public void deleteInvoice(InvoiceDTO invoiceDTO){
        Invoice invoice3 = invoiceDAO.getById(invoiceDTO.getId());
        invoiceDAO.delete(invoice3);
    }

    @Override
    public Invoice getInvoiceById(Long id){
        return invoiceDAO.getById(id);
    }

    @Override
    public List<Invoice> getInvoiceBySellingDate(Date sellingDate){
        return invoiceDAO.getInvoiceBySellingDate(sellingDate);
    }

    @Override
    public List<Invoice> getInvoiceByConfirmDate(Date confirmDate){
        return invoiceDAO.getInvoiceByConfirmDate(confirmDate);
    }

    @Override
    public List<Invoice> getInvoiceByInvoiceDate(Date invoiceDate){
        return invoiceDAO.getInvoiceByInvoiceDate(invoiceDate);
    }

    @Override
    public InvoiceDTO getInvoiceByNumber(String number){
        return mapperFacade.map(invoiceDAO.getInvoiceByNumber(number), InvoiceDTO.class);
    }


    @Override
    public  List<InvoiceDTO> getAllInvoices(){
        List<InvoiceDTO> invoices = new ArrayList<>();
        invoiceDAO.getAll().forEach(invoice -> invoices.add(mapperFacade.map(invoice, InvoiceDTO.class)));
        return invoices;
    }

    public void addInvoiceItem2Invoice(Invoice invoice, InvoiceItem invoiceItem){
        if(invoiceItem.getInvoice() != invoice) {
            invoiceItem.setInvoice(invoice);
            invoiceItemDAO.update(invoiceItem);
        }
        if (!invoiceDAO.getInvoiceItems(invoice).contains(invoiceItem)){
            List<InvoiceItem> invoiceItems = invoiceDAO.getInvoiceItems(invoice);
            invoiceItems.add(invoiceItem);
            invoice.setInvoiceItems(invoiceItems);
            invoiceDAO.update(invoice);

        }
    }

    @Override
    public void deleteInvoiceItemFromInvoice(Invoice invoice, InvoiceItem invoiceItem){
        if(invoiceItem.getInvoice() == invoice){
            invoiceItem.setInvoice(null);
            invoiceItemDAO.update(invoiceItem);

        }
        if(invoiceDAO.getInvoiceItems(invoice).contains(invoiceItem)){

            List<InvoiceItem> invoiceItems2 = invoiceDAO.getInvoiceItems(invoice);
            invoiceItems2.remove(invoiceItem);
            invoice.setInvoiceItems(invoiceItems2);
            invoiceDAO.update(invoice);
        }
    }

    @Override
    public List<InvoiceItem> getInvoiceItems(Invoice invoice){
        return  invoiceDAO.getInvoiceItems(invoice);
    }

}