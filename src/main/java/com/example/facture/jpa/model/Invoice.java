package com.example.facture.jpa.model;

import org.bouncycastle.asn1.cms.TimeStampedData;
import sun.util.calendar.LocalGregorianCalendar;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import lombok.Setter;
import lombok.Getter;

@Entity
@Getter @Setter
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
    private Date sellingDate;

    @Column(name = "invoicedate")
    @NotNull
    private Date invoiceDate;

    @Column(name = "sum")
    @NotNull
    private double sum;

    @Column(name = "confirmdate")
    @NotNull
    private Date confirmDate;


    public Invoice() {

    }



    public Invoice(String number, Date sellingDate, Date invoiceDate, double sum, Date confirmDate) {
        this.number = number;
        this.sellingDate = sellingDate;
        this.invoiceDate = invoiceDate;
        this.sum = sum;
        this.confirmDate = confirmDate;
    }

    /*
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

    public Date getSellingDate(){
        return sellingDate;
    }

    public void setSellingDate(Date sellingDate){
        this.sellingDate = sellingDate;
    }

    public Date getInvoiceDate(){
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate){
        this.invoiceDate = invoiceDate;
    }

    public double getSum(){
        return sum;
    }

    public void setSum(double sum){
        this.sum = sum;
    }

    public Date getConfirmDate(){
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate){
        this.confirmDate = confirmDate;
    }

*/


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;



    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<InvoiceItem> invoiceItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;






}

