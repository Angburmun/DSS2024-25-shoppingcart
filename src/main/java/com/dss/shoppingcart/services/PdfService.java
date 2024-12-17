package com.dss.shoppingcart.services;

import com.dss.shoppingcart.models.Product;
import org.springframework.stereotype.Service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PdfService {
	
	private int counter = 0;

    public byte[] generateInvoice(List<Product> products) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, byteArrayOutputStream);

            document.open();

            // Encabezado
            Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            document.add(new Paragraph("ShoppingCart! - Factura", boldFont));
            document.add(new Paragraph(" ")); // Espacio

            // Fecha y Número de Factura
            Font regularFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
            String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            document.add(new Paragraph("Fecha: " + currentDate, regularFont));
            document.add(new Paragraph("Número de Factura: #" + (int) (++counter), regularFont));
            document.add(new Paragraph(" ")); // Espacio

            // Tabla de Productos
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{1, 5, 2, 2});

            // Encabezados de la Tabla
            addTableHeader(table, new String[]{"ID", "Producto", "Precio Unitario", "Precio Total"});

            double subtotal = 0;
            for (Product product : products) {
                table.addCell(String.valueOf(product.getId()));
                table.addCell(product.getName());
                table.addCell(String.format("%.2f €", product.getPrice()));
                table.addCell(String.format("%.2f €", product.getPrice())); // Asumimos 1 unidad por producto
                subtotal += product.getPrice();
            }
            document.add(table);

            // Cálculo de Totales
            document.add(new Paragraph(" ")); // Espacio
            double iva = subtotal * 0.21; // 21% de IVA
            double total = subtotal + iva;

            document.add(new Paragraph(String.format("Subtotal: %.2f €", subtotal), regularFont));
            document.add(new Paragraph(String.format("IVA (21%%): %.2f €", iva), regularFont));
            document.add(new Paragraph(String.format("Total: %.2f €", total), boldFont));

            // Pie de Página
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Gracias por su compra en ShoppingCart!", FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 10)));

            document.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error al generar la factura", e);
        }
    }

    private void addTableHeader(PdfPTable table, String[] headers) {
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(cell);
        }
    }
}
