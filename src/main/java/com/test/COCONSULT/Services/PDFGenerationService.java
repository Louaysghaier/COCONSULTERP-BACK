package com.test.COCONSULT.Services;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.stream.Stream;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
public class PDFGenerationService {

    public byte[] generatePDF() throws DocumentException, IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document doc = new Document();
        PdfWriter.getInstance(doc, outputStream);
        doc.open();

        // Add text content
        Paragraph paragraph = new Paragraph("Lorum ipsum some text before image. Lorum ipsum some text before image. Lorum ipsum some text before image. Lorum ipsum some text before image. Lorum ipsum some text before image. Lorum ipsum some text before image. Lorum ipsum some text before image. Lorum ipsum some text before image.\n");
        doc.add(paragraph);

        // Add table
        PdfPTable table = new PdfPTable(3);
        addTableContent(table);
        doc.add(table);

        // Close document
        doc.close();

        return outputStream.toByteArray();
    }

    private void addTableContent(PdfPTable table) throws DocumentException, IOException {
        tableHeader(table);
        addRow(table);

    }

    private void tableHeader(PdfPTable table) {
        Stream.of("Id", "First Name", "Last Name").forEach(title -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.CYAN);
            header.setBorderWidth(1);
            header.setPhrase(new Phrase(title));
            table.addCell(header);
        });
    }

    private void addRow(PdfPTable table) {
        table.addCell("1");
        table.addCell("PDF");
        table.addCell("Generation");
    }
}
