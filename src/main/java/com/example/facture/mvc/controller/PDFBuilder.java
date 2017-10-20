package com.example.facture.mvc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.facture.jpa.dto.InvoiceDTO;
import com.example.facture.jpa.model.Invoice;
import com.example.facture.jpa.model.InvoiceItem;
import com.example.facture.jpa.model.TypeOfCustomer;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Martynka on 20.10.2017.
 */
public class PDFBuilder extends AbstractITextPdfView {


    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
                                    PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // get data model which is passed by the Spring container


        Invoice inv = (Invoice) model.get("listInvoices");
        List<InvoiceItem> item = (List<InvoiceItem>) model.get("listOfInvoiceItems");

        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[]{3.0f});
        table.setSpacingBefore(10);

        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);

        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.BLUE);
        cell.setPadding(5);

        // write table header
        cell.setPhrase(new Phrase("NUMBER INVOICE", font));
        table.addCell(cell);



        // write table row data


        table.addCell(inv.getNumberr());
        doc.add(table);
        PdfPTable tablee = new PdfPTable(3);
        tablee.setWidthPercentage(100.0f);
        tablee.setWidths(new float[]{3.0f, 3.0f, 3.0f});
        tablee.setSpacingBefore(10);


        // define table header cell
        PdfPCell celll = new PdfPCell();
        celll.setBackgroundColor(BaseColor.BLUE);
        celll.setPadding(5);


        celll.setPhrase(new Phrase("NUMBER ITEM", font));
        tablee.addCell(celll);

        celll.setPhrase(new Phrase("BRUTTO PRICE", font));
        tablee.addCell(celll);

        celll.setPhrase(new Phrase("NAME", font));
        tablee.addCell(celll);

        for (InvoiceItem aItem : item) {

            tablee.addCell(String.valueOf(aItem.getNumber()));
            tablee.addCell(String.valueOf(aItem.getProduct().getBruttoPrice()));
            tablee.addCell(aItem.getProduct().getName());



        }
        doc.add(tablee);



    }

}
