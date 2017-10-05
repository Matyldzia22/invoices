package com.example.facture.jpa.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Entity
@NoArgsConstructor
@Table(name = "customer")
@Data
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

    @Override
    public String toString()
    {
        return String.format("Customer[%s]", name);
    }
}