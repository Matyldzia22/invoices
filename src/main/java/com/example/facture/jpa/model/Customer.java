package com.example.facture.jpa.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;


import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude={"priceGroup", "typeOfCustomer"})
@Table(name = "customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    @NotBlank
    private String email;

    @Column(name = "nip")
    @NotBlank
    private String nip;

    @Column(name = "phoneNumber")
    @NotBlank
    private String phoneNumber;

    @Column(name = "name")
    @NotBlank
    private String name;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "price_group_id")
    private PriceGroup priceGroup;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "type_of_customer_id")
    private TypeOfCustomer typeOfCustomer;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Invoice> invoices = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    @Override
    public String toString() {
        return String.format("Customer[%d]", id);
    }
}