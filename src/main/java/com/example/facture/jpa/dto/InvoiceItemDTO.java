package com.example.facture.jpa.dto;
import com.example.facture.jpa.model.Invoice;
import com.example.facture.jpa.model.Product;
import lombok.Data;



public @Data class InvoiceItemDTO {

    private Long id;

    public InvoiceItemDTO(int number, ProductDTO product, InvoiceDTO invoice ) {
        this.number = number;
        this.product = product;
        this.invoice = invoice;
    }

    public InvoiceItemDTO(int number, long idInvoice, long idProduct) {
        this.number = number;
        this.idInvoice = idInvoice;
        this.idProduct = idProduct;
    }

    private int number;

    private long idInvoice;
    private long idProduct;


   private InvoiceDTO invoice;
   private ProductDTO product;





}