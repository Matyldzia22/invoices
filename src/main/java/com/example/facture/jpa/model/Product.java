package com.example.facture.jpa.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@NoArgsConstructor
@Table(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "netto_price")
    @NotNull
    private double nettoPrice;

    @Column(name = "vat")
    @NotNull
    @Range(min=5,max=27)
    private int vat;

    @Column(name = "brutto_price")
    @NotNull
    private double bruttoPrice;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<InvoiceItem> invoiceItems = new ArrayList<>();

    @Override
    public String toString()
    {
        return String.format("Product[%s]", name);
    }

}
