package com.example.facture.jpa.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "street")
    @NotNull
    private String street;

    @Column(name = "number")
    @NotNull
    private String number;

    @Column(name = "city")
    @NotNull
    private String city;

    @Column(name = "postcode")
    @NotNull
    private String postcode;




    public Address(){

    }

    /*public Address(String street) {
        this.street = street;
    }*/

    public Address(String street, String number, String city, String postcode){
        this.street = street;
        this.number = number;
        this.city = city;
        this.postcode = postcode;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet(){
        return street;
    }

    public void setStreet(String street){
        this.street = street;
    }

    public String getNumber(){
        return number;
    }

    public void setNumber(String number){
        this.number = number;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getPostcode(){
        return postcode;
    }

    public void setPostcode(String postcode){
        this.postcode = postcode;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private List<Invoice> invoices = new ArrayList<>();









}