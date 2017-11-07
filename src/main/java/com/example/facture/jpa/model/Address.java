package com.example.facture.jpa.model;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Table(name = "address")
@EqualsAndHashCode(exclude={"customer", "typeOfAddress"})

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

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dataFrom;

    private Date dataTo;


    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private List<Invoice> invoices = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "type_of_address_id")
    private TypeOfAddress typeOfAddress;

    @Override
    public String toString() {
        return String.format("Address[%s %s]", street, city);
    }


}