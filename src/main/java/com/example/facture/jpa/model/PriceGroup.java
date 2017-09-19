package com.example.facture.jpa.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.Setter;
import lombok.Getter;

@Entity
@Getter @Setter
@Table(name = "price_group")
public class PriceGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "discount")
    @NotNull
    private int discount;

    @Column(name = "name")
    @NotNull
    private String name;


    public PriceGroup(){

    }

    public PriceGroup(int discount, String name) {

        this.discount = discount;
        this.name = name;
    }

/*
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscount(){
        return discount;
    }

    public void setDiscount(int discount){
        this.discount = discount;
    }

    */

    @OneToMany(mappedBy = "priceGroup", cascade = CascadeType.ALL)
    private List<Customer> customers = new ArrayList<>();

}