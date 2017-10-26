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

@Entity
@NoArgsConstructor
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


    @OneToMany(mappedBy = "taxBracket", cascade = CascadeType.ALL)
    private List<Customer> customers = new ArrayList<>();

    @Override
    public String toString()
    {
        return String.format("TaxBracket[%d]", number);
    }


}