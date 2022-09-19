package com.example.balcamgym.Utils;


import com.example.balcamgym.DTO.BillDTO;

import com.example.balcamgym.Models.Product;
import com.example.balcamgym.Services.ProductServices;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PdfGenerator {

    @Autowired
    private static ProductServices productServices;

    public static void createBill (List<Long> ids, BillDTO billDTO){
        Font titleFont = new Font(Font.FontFamily.HELVETICA,18);
        Font subFont = new Font(Font.FontFamily.HELVETICA,12);
        Font headersFont = new Font(Font.FontFamily.HELVETICA,14);

        try {
            Document document = new Document(PageSize.A4);
            String route = System.getProperty("user.home");
            PdfWriter.getInstance(document, new FileOutputStream(route + "/download/BALCAM_BILL.pdf"));


            document.open();
            document.setMargins(2,2,2,2);



            Paragraph title = new Paragraph("BALCAM'S GYM-BILL RESUMEN"+ billDTO.getClient().getFirstName()+" "+billDTO.getClient().getLastName() + billDTO.getNumber() ,titleFont);
            title.setSpacingAfter(3);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingBefore(-2);

            Paragraph subTitle = new Paragraph("Account number: " + billDTO.getNumber());
            subTitle.setAlignment(Element.ALIGN_CENTER);
            subTitle.setSpacingAfter(1);

            Paragraph date = new Paragraph("Current date: " + LocalDate.now(), subFont);
            date.setSpacingAfter(6);
            date.setAlignment(Element.ALIGN_CENTER);




            Image img = Image.getInstance("src/main/resources/static/web/assets/images/logofinal.png");
            img.scaleAbsoluteWidth(200);
            img.scaleAbsoluteHeight(100);
            img.setAlignment(Element.ALIGN_CENTER);


            PdfPTable pdfPTable = new PdfPTable(5);
            PdfPCell pdfPCell1 = new PdfPCell(new Paragraph("Name", headersFont));
            PdfPCell pdfPCell2 = new PdfPCell(new Paragraph("Description", headersFont));
            PdfPCell pdfPCell3 = new PdfPCell(new Paragraph("Price", headersFont));
            PdfPCell pdfPCell4 = new PdfPCell(new Paragraph("Quantity", headersFont));
            PdfPCell pdfPCell5 = new PdfPCell(new Paragraph("Total amount",headersFont));
            pdfPCell1.setBorder(0);
            pdfPCell2.setBorder(0);
            pdfPCell3.setBorder(0);
            pdfPCell4.setBorder(0);
            pdfPCell5.setBorder(0);
            pdfPTable.addCell(pdfPCell1);
            pdfPTable.addCell(pdfPCell2);
            pdfPTable.addCell(pdfPCell3);
            pdfPTable.addCell(pdfPCell4);
            pdfPTable.addCell(pdfPCell5);

            Set<Product> setProducts = ids.stream().map(id -> productServices.findProductById(id)).collect(Collectors.toSet());
            List<Product> products = ids.stream().map(id -> productServices.findProductById(id)).collect(Collectors.toList());



            setProducts.forEach(product -> {
                int quantity = Collections.frequency(products.stream().map(Product::getName).collect(Collectors.toList()), product.getName());
                PdfPCell pdfPCell6 = new PdfPCell(new Paragraph(product.getName() , subFont));
                PdfPCell pdfPCell7 = new PdfPCell(new Paragraph(product.getDescription(), subFont));
                PdfPCell pdfPCell8 = new PdfPCell(new Paragraph("$" + String.valueOf(product.getPrice()), subFont));
                PdfPCell pdfPCell9 = new PdfPCell(new Paragraph(String.valueOf(quantity), subFont));
                PdfPCell pdfPCell10 = new PdfPCell(new Paragraph("$"+String.valueOf(product.getPrice()*quantity)));
                pdfPCell6.setBorder(1);
                pdfPCell7.setBorder(1);
                pdfPCell8.setBorder(1);
                pdfPCell9.setBorder(1);
                pdfPCell10.setBorder(1);

                pdfPTable.addCell(pdfPCell6);
                pdfPTable.addCell(pdfPCell7);
                pdfPTable.addCell(pdfPCell8);
                pdfPTable.addCell(pdfPCell9);
                pdfPTable.addCell(pdfPCell10);
            });

            document.add(img);
            document.add(title);
            document.add(subTitle);
            document.add(date);
            document.add(pdfPTable);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
