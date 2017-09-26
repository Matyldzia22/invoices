package com.example.facture.jpa.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bouncycastle.asn1.cms.TimeStampedData;
import sun.util.calendar.LocalGregorianCalendar;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import lombok.Setter;
import lombok.Getter;

@Entity
@NoArgsConstructor
@Table(name = "invoice")
@Data
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    @NotNull
    private String number;

    @Column(name = "sellingdate")
    @NotNull
    private Date sellingDate;

    @Column(name = "invoicedate")
    @NotNull
    private Date invoiceDate;

    @Column(name = "sum")
    @NotNull
    private double sum;

    @Column(name = "confirmdate")
    @NotNull
    private Date confirmDate;




    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;



    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<InvoiceItem> invoiceItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;






}

