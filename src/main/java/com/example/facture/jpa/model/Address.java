package com.example.facture.jpa.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

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
    @NotBlank
    private String street;

    @Column(name = "number")
    @NotBlank
    private String number;

    @Column(name = "city")
    @NotBlank
    private String city;

    @Column(name = "postCode")
    @NotBlank
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