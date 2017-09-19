package com.example.facture.jpa.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

import java.util.ArrayList;
import java.util.List;

import lombok.Setter;
import lombok.Getter;

@Entity
@Getter @Setter
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
    private double nettoPrice;

    @Column(name = "vat")
    @NotNull
    private int vat;

    @Column(name = "brutto_price")
    @NotNull
    private double bruttoPrice;




    public Product() {

    }
    /*public Product(String name) {
        this.name = name;
    }*/

    public Product(String name, double nettoPrice, int vat, double bruttoPrice){
        this.name = name;
        this.nettoPrice = nettoPrice;
        this.vat = vat;
        this.bruttoPrice = bruttoPrice;
    }

/*
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

    public double getNettoPrice(){
        return nettoPrice;
    }

    public void setNettoPrice(double nettoPrice){
        this.nettoPrice = nettoPrice;
    }

    public double getBruttoPrice(){
        return bruttoPrice;
    }

    public void setBruttoPrice(double bruttoPrice){
        this.bruttoPrice = bruttoPrice;
    }

    public int getVat(){
        return vat;
    }

    public void setVat(int vat){
        this.vat = vat;
    }

*/


//Invoices

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<InvoiceItem> invoiceItems = new ArrayList<>();

}

