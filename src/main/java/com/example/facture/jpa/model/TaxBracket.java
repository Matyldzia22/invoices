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
@Table(name = "tax_bracket")
@Data
public class TaxBracket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    @NotNull
    private int number;



    public TaxBracket(){

    }

    @OneToMany(mappedBy = "taxBracket", cascade = CascadeType.ALL)
    private List<Customer> customers = new ArrayList<>();





}