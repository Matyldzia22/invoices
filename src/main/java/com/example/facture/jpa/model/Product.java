package com.example.facture.jpa.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public  class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "netto_price")
    @NotNull
    private double netto_price;

    @Column(name = "vat")
    @NotNull
    private int vat;

    @Column(name = "brutto_price")
    @NotNull
    private double brutto_price;




    public Product() {

    }
    /*public Product(String name) {
        this.name = name;
    }*/

    public Product(String name, double netto_price, int vat, double brutto_price){
        this.name = name;
        this.netto_price = netto_price;
        this.vat = vat;
        this.brutto_price = brutto_price;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public double getNetto_price(){
        return netto_price;
    }

    public void setNetto_price(double netto_price){
        this.netto_price = netto_price;
    }

    public double getBrutto_price(){
        return brutto_price;
    }

    public void setBrutto_price(double brutto_price){
        this.brutto_price = brutto_price;
    }

    public int getVat(){
        return vat;
    }

    public void setVat(int vat){
        this.vat = vat;
    }




//Invoices

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<InvoiceItem> invoiceItems = new ArrayList<>();

}

