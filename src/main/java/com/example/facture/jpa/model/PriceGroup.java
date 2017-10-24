package com.example.facture.jpa.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Entity
@NoArgsConstructor
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
    @Pattern(regexp = "[A-Za-z]+", message = "{invalid.pattern.name}")
    private String name;


    @OneToMany(mappedBy = "priceGroup", cascade = CascadeType.ALL)
    private List<Customer> customers = new ArrayList<>();

    @Override
    public String toString()
    {
        return String.format("PriceGroup[%s]", name);
    }

}