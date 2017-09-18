package com.example.facture.jpa.model;

import org.bouncycastle.asn1.cms.TimeStampedData;
import sun.util.calendar.LocalGregorianCalendar;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    @NotNull
    private String number;

    @Column(name = "sellingdate")
    @NotNull
    private Date sellingdate;

    @Column(name = "invoicedate")
    @NotNull
    private Date invoicedate;

    @Column(name = "sum")
    @NotNull
    private double sum;

    @Column(name = "confirmdate")
    @NotNull
    private Date confirmdate;


    public Invoice() {

    }

    public Invoice(String number) {
        this.number = number;
    }

    public Invoice(String number, Date sellingdate, Date invoicedate, double sum, Date confirmdate) {
        this.number = number;
        this.sellingdate = sellingdate;
        this.invoicedate = invoicedate;
        this.sum = sum;
        this.confirmdate = confirmdate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber(){
        return number;
    }

    public void setNumber(String number){
        this.number = number;
    }

    public Date getSellingdate(){
        return sellingdate;
    }

    public void setSellingdate(Date sellingdate){
        this.sellingdate = sellingdate;
    }

    public Date getInvoicedate(){
        return invoicedate;
    }

    public void setInvoicedate(Date invoicedate){
        this.invoicedate = invoicedate;
    }

    public double getSum(){
        return sum;
    }

    public void setSum(double sum){
        this.sum = sum;
    }

    public Date getConfirmdate(){
        return confirmdate;
    }

    public void setConfirmdate(Date confirmdate){
        this.confirmdate = confirmdate;
    }



    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;



    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<InvoiceItem> invoiceItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;






}

