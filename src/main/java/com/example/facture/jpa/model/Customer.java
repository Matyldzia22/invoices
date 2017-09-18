package com.example.facture.jpa.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstName")
    @NotNull
    private String firstName;

    @Column(name = "lastName")
    @NotNull
    private String lastName;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "nip")
    @NotNull
    private String nip;

    @Column(name = "phoneNumber")
    @NotNull
    private String phoneNumber;

    @Column(name = "name")
    @NotNull
    private String name;



    public Customer(){

    }

    /*public Customer(String name) {
        this.name = name;
    }*/

    public Customer(String firstName, String lastName, String email, String nip, String phoneNumber, String name) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.nip = nip;
        this.phoneNumber = phoneNumber;
        this.name = name;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }



    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getNip(){
        return nip;
    }

    public void setNip(String nip){
        this.nip = nip;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }





    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "price_group_id")
    private PriceGroup priceGroup;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "tax_bracket_id")
    private TaxBracket taxBracket;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "type_of_customer_id")
    private TypeOfCustomer typeOfCustomer;



    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Invoice> invoices = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();




}