package com.example.facture.mvc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.facture.jpa.dto.InvoiceDTO;
import com.example.facture.jpa.model.Invoice;
import com.example.facture.jpa.model.InvoiceItem;
import com.example.facture.jpa.model.TypeOfCustomer;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.web.servlet.ModelAndView;


public class PDFBuilder extends AbstractITextPdfView {


    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
                                    PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        Invoice inv = (Invoice) model.get("listInvoices");
        List<InvoiceItem> item = (List<InvoiceItem>) model.get("listOfInvoiceItems");


        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[]{3.0f, 3.0f, 3.0f, 3.0f, 3.0f});
        table.setSpacingBefore(10);

        // define font for table header row
        // Font font = FontFactory.getFont(Font.FontFamily.HELVETICA,10);
        Font smallfont = new Font(Font.FontFamily.HELVETICA, 10);
        smallfont.setColor(BaseColor.WHITE);

        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setPadding(5);

        // write table header
        cell.setPhrase(new Phrase("INVOICE NUMBER", smallfont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("CONFIRM DATE", smallfont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("INVOICE DATE", smallfont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("SELLING DATE", smallfont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("SUM", smallfont));
        table.addCell(cell);


        // write table row data


        table.addCell(inv.getNumberr());
        table.addCell(String.valueOf(inv.getConfirmDate()));
        table.addCell(String.valueOf(inv.getInvoiceDate()));
        table.addCell(String.valueOf(inv.getSellingDate()));
        table.addCell(String.valueOf(inv.getSum()));
        doc.add(table);


        PdfPTable tablee = new PdfPTable(5);
        tablee.setWidthPercentage(100.0f);
        tablee.setWidths(new float[]{3.0f, 3.0f, 3.0f, 3.0f, 3.0f});
        tablee.setSpacingBefore(10);


        // define table header cell
        PdfPCell celll = new PdfPCell();
        celll.setBackgroundColor(BaseColor.LIGHT_GRAY);
        celll.setPadding(5);

        celll.setPhrase(new Phrase("NAME", smallfont));
        tablee.addCell(celll);

        celll.setPhrase(new Phrase("BRUTTO PRICE", smallfont));
        tablee.addCell(celll);

        celll.setPhrase(new Phrase("NETTO PRICE", smallfont));
        tablee.addCell(celll);

        celll.setPhrase(new Phrase("NUMBER", smallfont));
        tablee.addCell(celll);

        celll.setPhrase(new Phrase("VAT", smallfont));
        tablee.addCell(celll);

        for (InvoiceItem aItem : item) {

            tablee.addCell(aItem.getProduct().getName());
            tablee.addCell(String.valueOf(aItem.getProduct().getBruttoPrice()));
            tablee.addCell(String.valueOf(aItem.getProduct().getNettoPrice()));
            tablee.addCell(String.valueOf(aItem.getNumber()));
            tablee.addCell(String.valueOf(aItem.getProduct().getVat())+'%');


        }
        doc.add(tablee);

        PdfPTable table2 = new PdfPTable(5);
        table2.setWidthPercentage(100.0f);
        table2.setWidths(new float[]{10.0f, 10.0f, 10.0f, 10.0f, 10.0f});
        table2.setSpacingBefore(10);


        // define table header cell
        PdfPCell cell2 = new PdfPCell();
        cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell2.setPadding(5);

        cell2.setPhrase(new Phrase("NAME", smallfont));
        cell2.setFixedHeight(30);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table2.addCell(cell2);
        cell2.setPhrase(new Phrase("FIRST NAME", smallfont));
        cell2.setFixedHeight(30);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table2.addCell(cell2);
        cell2.setPhrase(new Phrase("LAST NAME", smallfont));
        cell2.setFixedHeight(30);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table2.addCell(cell2);
        cell2.setPhrase(new Phrase("NIP", smallfont));
        cell2.setFixedHeight(30);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table2.addCell(cell2);
        cell2.setPhrase(new Phrase("PHONE NUMBER", smallfont));
        cell2.setFixedHeight(30);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table2.addCell(cell2);


        table2.addCell(inv.getCustomer().getName());
        table2.addCell(inv.getCustomer().getFirstName());
        table2.addCell(inv.getCustomer().getLastName());
        table2.addCell(inv.getCustomer().getNip());
        table2.addCell(inv.getCustomer().getPhoneNumber());


        doc.add(table2);


        PdfPTable table4 = new PdfPTable(3);
        table4.setWidthPercentage(100.0f);
        table4.setWidths(new float[]{10.0f, 10.0f, 10.0f});
        table4.setSpacingBefore(10);

        // define table header cell
        PdfPCell cell4 = new PdfPCell();
        cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell4.setPadding(5);

        cell4.setPhrase(new Phrase("TYPE OF CUSTOMER", smallfont));
        cell4.setFixedHeight(30);
        cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table4.addCell(cell4);
        cell4.setPhrase(new Phrase("PRICE GROUP", smallfont));
        cell4.setFixedHeight(30);
        cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table4.addCell(cell4);
        cell4.setPhrase(new Phrase("TAX BRACKET", smallfont));
        cell4.setFixedHeight(30);
        cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table4.addCell(cell4);

        table4.addCell(inv.getCustomer().getTypeOfCustomer().getName());
        table4.addCell(inv.getCustomer().getPriceGroup().getName());
        table4.addCell(String.valueOf(inv.getCustomer().getTaxBracket().getNumber()) + '%');

        doc.add(table4);

        PdfPTable table3 = new PdfPTable(4);
        table3.setWidthPercentage(100.0f);
        table3.setWidths(new float[]{3.0f, 3.0f, 3.0f, 3.0f});
        table3.setSpacingBefore(10);


        // define table header cell
        PdfPCell cell3 = new PdfPCell();
        cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell3.setPadding(5);

        cell3.setPhrase(new Phrase("STREET", smallfont));
        table3.addCell(cell3);
        cell3.setPhrase(new Phrase("NUMBER", smallfont));
        table3.addCell(cell3);
        cell3.setPhrase(new Phrase("POSTCODE", smallfont));
        table3.addCell(cell3);
        cell3.setPhrase(new Phrase("CITY", smallfont));
        table3.addCell(cell3);


        table3.addCell(inv.getAddress().getStreet());
        table3.addCell(inv.getAddress().getNumber());
        table3.addCell(inv.getAddress().getPostCode());
        table3.addCell(inv.getAddress().getCity());


        doc.add(table3);


    }

}
