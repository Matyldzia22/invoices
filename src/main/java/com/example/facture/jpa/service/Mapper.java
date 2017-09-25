package com.example.facture.jpa.service;
import com.example.facture.jpa.dto.*;
import com.example.facture.jpa.model.*;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper extends ConfigurableMapper {

    protected void configure(MapperFactory factory) {
        factory.classMap(Address.class, AddressDTO.class)
                .byDefault()
                .register();

        factory.classMap(Customer.class, CustomerDTO.class)
                .byDefault()
                .register();
        factory.classMap(Product.class, ProductDTO.class)
                .byDefault()
                .register();

        factory.classMap(PriceGroup.class, PriceGroupDTO.class)
                .byDefault()
                .register();
        factory.classMap(TaxBracket.class, TaxBracketDTO.class)
                .byDefault()
                .register();
        factory.classMap(TypeOfCustomer.class, TypeOfCustomerDTO.class)
                .byDefault()
                .register();

        factory.classMap(Address.class, AddressDTO.class)
                .byDefault()
                .register();

        factory.classMap(Invoice.class, InvoiceDTO.class)
                .byDefault()
                .register();

        factory.classMap(InvoiceItem.class, InvoiceItemDTO.class)
                .byDefault()
                .register();

        factory.classMap(Customer.class, CustomerDisplayDTO.class)
                .byDefault()
                .register();

        factory.classMap(Invoice.class, InvoiceDTO.class)
                .byDefault()
                .register();
    }
}