package com.example.facture.jpa.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import lombok.Data;
import lombok.Setter;
import lombok.Getter;

@Entity

@Table(name = "price_group")
@Data
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

    @OneToMany(mappedBy = "priceGroup", cascade = CascadeType.ALL)
    private List<Customer> customers = new ArrayList<>();

}