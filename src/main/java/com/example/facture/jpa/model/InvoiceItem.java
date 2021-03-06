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
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@NoArgsConstructor
@Table(name = "invoiceitem")
@Data
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    @NotNull
    @Range(min=1)
    private int number;


    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;


    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "product_id")
    private Product product;

    @Override
    public String toString() {
        return String.format("InvoiceItem[%d, %d]", id, number);
    }


}