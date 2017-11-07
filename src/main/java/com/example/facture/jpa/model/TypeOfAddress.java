package com.example.facture.jpa.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@NoArgsConstructor
@Table(name = "type_of_address")
@Data
public class TypeOfAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotBlank
    private String name;


    @OneToMany(mappedBy = "typeOfAddress", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    @Override
    public String toString() {
        return String.format("TypeOfAddress[%s]", name);
    }


}