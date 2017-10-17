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

public class InvoiceItemServiceImpl implements InvoiceItemService {

    @Autowired
    private InvoiceItemDAO invoiceItemDAO;

    @Autowired
    private InvoiceDAO invoiceDAO;

    @Autowired
    private ProductDAO productDAO;


    @Autowired
    private MapperFacade mapperFacade;


    @Override
    public void saveInvoiceItem(InvoiceItemDTO invoiceItemDTO) {
        InvoiceItem invoiceItem = mapperFacade.map(invoiceItemDTO, InvoiceItem.class);
        addProduct2InvoiceItem(invoiceItem, productDAO.getById(invoiceItemDTO.getIdProduct()));
        addInvoice2InvoiceItem(invoiceItem, invoiceDAO.getById(invoiceItemDTO.getIdInvoice()));


        invoiceItemDAO.save(invoiceItem);
    }

    @Override
    public void updateInvoiceItem(InvoiceItem invoiceItem) {

        invoiceItemDAO.update(invoiceItem);
    }

    @Override
    public void deleteInvoiceItem(InvoiceItem invoiceItem) {

        invoiceItemDAO.delete(invoiceItem);
    }

    @Override
    public InvoiceItem getInvoiceItemById(Long id) {
        return invoiceItemDAO.getById(id);
    }

    @Override
    public void addProduct2InvoiceItem(InvoiceItem invoiceItem, Product product) {
        if (invoiceItem.getProduct() != product) {
            invoiceItem.setProduct(product);
            invoiceItemDAO.update(invoiceItem);
        }
        List<InvoiceItem> invoiceItems = productDAO.getInvoiceItems(product);
        if (!invoiceItems.contains(invoiceItem)) {
            invoiceItems.add(invoiceItem);
            product.setInvoiceItems(invoiceItems);
            productDAO.update(product);
        }
    }

    @Override
    public void deleteProductFromInvoiceItem(InvoiceItem invoiceItem, Product product) {
        if (invoiceItem.getProduct() == product) {
            invoiceItem.setProduct(null);
            invoiceItemDAO.update(invoiceItem);
        }
        List<InvoiceItem> invoiceItems2 = productDAO.getInvoiceItems(product);
        if (invoiceItems2.contains(invoiceItem)) {
            invoiceItems2.remove(invoiceItem);
            product.setInvoiceItems(invoiceItems2);
            productDAO.update(product);
        }
    }

    @Override
    public void addInvoice2InvoiceItem(InvoiceItem invoiceItem, Invoice invoice) {
        if (invoiceItem.getInvoice() != invoice) {
            invoiceItem.setInvoice(invoice);
            invoiceItemDAO.update(invoiceItem);
        }
        List<InvoiceItem> invoiceItems3 = invoiceDAO.getInvoiceItems(invoice);
        if (!invoiceItems3.contains(invoiceItem)) {
            invoiceItems3.add(invoiceItem);
            invoice.setInvoiceItems(invoiceItems3);
            invoiceDAO.update(invoice);
        }
    }

    @Override
    public void deleteInvoiceFromInvoiceItem(InvoiceItem invoiceItem, Invoice invoice) {
        if (invoiceItem.getInvoice() == invoice) {
            invoiceItem.setInvoice(null);
            invoiceItemDAO.update(invoiceItem);
        }
        List<InvoiceItem> invoiceItems = invoiceDAO.getInvoiceItems(invoice);
        if (invoiceItems.contains(invoiceItem)) {
            invoiceItems.remove(invoiceItem);
            invoice.setInvoiceItems(invoiceItems);
            invoiceDAO.update(invoice);
        }
    }

    @Override
    public List<InvoiceItem> getAllInvoiceItems() {
        return invoiceItemDAO.getAll();
    }

    @Override
    public long getInvId() {
        return invoiceItemDAO.getInvId();
    }


    @Override
    public List<InvoiceItem> getInvoiceItemByIdInvoice(Long idInvoice) {
        return invoiceItemDAO.getInvoiceItemByIdInvoice(idInvoice);
    }

    @Override
    public List<InvoiceItem> getInvoiceItemByIdProduct(Long idProduct) {
        return invoiceItemDAO.getInvoiceItemByIdProduct(idProduct);
    }

    @Override
    public List<InvoiceItem> getInvoiceItems(long idInvoice, long idProduct) {
        return invoiceItemDAO.getInvoiceItems(idInvoice, idProduct);
    }


}