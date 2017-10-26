package com.example.facture.jpa.Validator;

import com.example.facture.jpa.dto.CustomerDTO;
import com.example.facture.jpa.dto.InvoiceDTO;
import com.example.facture.jpa.model.Customer;
import com.example.facture.jpa.model.Invoice;
import com.example.facture.jpa.service.CustomerService;
import com.example.facture.jpa.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;


@Component
public class InvoiceValidator implements Validator {


    private InvoiceService invoiceService;

    public InvoiceValidator (InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }

    @Override
    public boolean supports(Class clazz) {
        return Invoice.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
       InvoiceDTO invoiceDTO = (InvoiceDTO) target;
        List<InvoiceDTO> invoiceList = invoiceService.getAllInvoices();
        for (InvoiceDTO invoicee : invoiceList) {
            if (invoicee.getNumberr().equals(invoiceDTO.getNumberr())) {
                errors.rejectValue("numberr", "", "Number already exist");
            }

        }
    }
}
