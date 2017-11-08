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
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import org.springframework.web.servlet.ModelAndView;

import static java.awt.SystemColor.text;


public class PDFBuilder extends AbstractITextPdfView {


    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
                                    PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        Invoice inv = (Invoice) model.get("listInvoices");
        List<InvoiceItem> item = (List<InvoiceItem>) model.get("listOfInvoiceItems");


        Font bigfont = new Font(Font.FontFamily.HELVETICA, 14);
        bigfont.setColor(BaseColor.BLACK);

        Chunk glue = new Chunk(new VerticalPositionMark());
        PdfPTable table10 = new PdfPTable(1);
        table10.setWidthPercentage(100.0f);
        table10.getDefaultCell().setBorder(0);
        table10.setPaddingTop(20);
        table10.setHorizontalAlignment(Element.ALIGN_LEFT);
        Phrase p = new Phrase();
        Phrase a = new Phrase();
        Phrase b = new Phrase();
        Phrase c = new Phrase();


        p.add("Sprzedawca :");
        p.add(glue);
        p.add("Nabywca :");
        a.add("Firma S.A.");
        a.add(glue);
        a.add(inv.getCustomer().getName());
        b.add("Aleje Jerozolimskie 5, 80-20 Warszawa");
        b.add(glue);
        b.add(inv.getAddress().getStreet() + " " + inv.getAddress().getNumber() + "," + inv.getAddress().getPostCode() + " " + inv.getAddress().getCity());
        c.add("NIP: 768-354-88-00");
        c.add(glue);
        c.add("NIP: " + inv.getCustomer().getNip());
        table10.addCell(p);
        table10.addCell(a);
        table10.addCell(b);
        table10.addCell(c);
        doc.add(table10);


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
            tablee.addCell(String.valueOf(aItem.getProduct().getVat()) + '%');


        }

        doc.add(tablee);


    }

}
