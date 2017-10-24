package com.example.facture.jpa.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "address")
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "street")
    @NotNull
    @Pattern(regexp = "[A-Za-z]+", message = "{invalid.pattern.street}")
    private String street;

    @Column(name = "number")
    @NotNull
    private String number;

    @Column(name = "city")
    @NotNull
    @Pattern(regexp = "[A-Za-z]+", message = "{invalid.pattern.city}")
    private String city;

    @Column(name = "postCode")
    @NotNull
    private String postCode;




    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private List<Invoice> invoices = new ArrayList<>();

    @Override
    public String toString()
    {
        return String.format("Address[%s %s]", street, city);
    }


}