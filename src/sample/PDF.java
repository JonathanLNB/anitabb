package sample;

import XML.Escena.MenuC;
import XML.TDA.Cliente;
import XML.TDA.Track;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by usuario on 28/05/17.
 */
public class PDF {
    private static final Font categoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static final Font subcategoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    Double total=0.0;
    public void createPDFFactura(File pdfNewFile, ObservableList<Track> lista, Cliente c) {
        try {
            Document document = new Document();


            PdfWriter.getInstance(document, new FileOutputStream(pdfNewFile));


            document.open();

            document.addTitle("MZone");
            document.addSubject("Tienda MZone");
            document.addKeywords("Factura, Compra");
            document.addAuthor("MZone");
            document.addCreator("Mzone");

            Anchor anchor = new Anchor("MZone", categoryFont);
            anchor.setName("Titulo");
            Paragraph chapTitle = new Paragraph(anchor.getContent(),
                    categoryFont);

            chapTitle.add(new Paragraph("\nLa siguiente tabla muestra las compras realizadas por el cliente "+c.getFirst_name()+" "+c.getLast_name()+": \n\n\n", subcategoryFont));

            Integer numColumns = 2;
            Integer numRows = lista.size();

            PdfPTable table = new PdfPTable(numColumns);

            PdfPCell columnHeader;


            columnHeader = new PdfPCell(new Phrase("Nombre"));
            columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(columnHeader);
            columnHeader = new PdfPCell(new Phrase("Precio"));
            columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(columnHeader);

            table.setHeaderRows(1);

            for (int row = 0; row < numRows; row++) {

                table.addCell(lista.get(row).getName());
                table.addCell(""+lista.get(row).getUnitPrice());
                total+=lista.get(row).getUnitPrice();

            }
            table.addCell("Total: ");

            table.addCell("$ "+total);

            document.add(chapTitle);
            document.add(table);
            document.close();

        } catch (DocumentException documentException) {
            System.out.println("The file not exists (Se ha producido un error al generar un documento): " + documentException);
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.println("No such file was found to generate the PDF "
                    + "(No se encontró el fichero para generar el pdf)" + fileNotFoundException);
        }
    }
    public void Empleados(File pdfNewFile) {
        try {
            Document document = new Document();


            PdfWriter.getInstance(document, new FileOutputStream(pdfNewFile));


            document.open();

            document.addTitle("MZone");
            document.addSubject("Tienda MZone");
            document.addKeywords("Factura, Compra");
            document.addAuthor("MZone");
            document.addCreator("Mzone");

            Anchor anchor = new Anchor("MZone", categoryFont);
            anchor.setName("Titulo");
            Paragraph chapTitle = new Paragraph(anchor.getContent(),
                    categoryFont);

            chapTitle.add(new Paragraph(Main.con.getEmpleadosRep(), subcategoryFont));

            document.add(chapTitle);
            document.close();

        } catch (DocumentException documentException) {
            System.out.println("The file not exists (Se ha producido un error al generar un documento): " + documentException);
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.println("No such file was found to generate the PDF "
                    + "(No se encontró el fichero para generar el pdf)" + fileNotFoundException);
        }
    }
    public void canciones(File pdfNewFile) {
        try {
            Document document = new Document();


            PdfWriter.getInstance(document, new FileOutputStream(pdfNewFile));


            document.open();

            document.addTitle("MZone");
            document.addSubject("Tienda MZone");
            document.addKeywords("Factura, Compra");
            document.addAuthor("MZone");
            document.addCreator("Mzone");

            Anchor anchor = new Anchor("MZone", categoryFont);
            anchor.setName("Titulo");
            Paragraph chapTitle = new Paragraph(anchor.getContent(),
                    categoryFont);
            ObservableList<Track> lista = Main.con.getSongs();
            Integer numColumns = 6;
            Integer numRows = lista.size();

            PdfPTable table = new PdfPTable(numColumns);

            PdfPCell columnHeader;


            columnHeader = new PdfPCell(new Phrase("Nombre"));
            columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(columnHeader);
            columnHeader = new PdfPCell(new Phrase("Album"));
            columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(columnHeader);
            columnHeader = new PdfPCell(new Phrase("Artista"));
            columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(columnHeader);
            columnHeader = new PdfPCell(new Phrase("Genero"));
            columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(columnHeader);
            columnHeader = new PdfPCell(new Phrase("Medio"));
            columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(columnHeader);
            columnHeader = new PdfPCell(new Phrase("Precio"));
            columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(columnHeader);
            table.setHeaderRows(1);

            for (int row = 0; row < numRows; row++) {

                table.addCell(lista.get(row).getName());
                table.addCell(""+lista.get(row).getAlbum());
                table.addCell(""+lista.get(row).getArtist());
                table.addCell(""+lista.get(row).getGenre());
                table.addCell(""+lista.get(row).getMediaType());
                table.addCell(""+lista.get(row).getUnitPrice());
            }

            document.add(chapTitle);
            document.add(table);
            document.close();

        } catch (DocumentException documentException) {
            System.out.println("The file not exists (Se ha producido un error al generar un documento): " + documentException);
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.println("No such file was found to generate the PDF "
                    + "(No se encontró el fichero para generar el pdf)" + fileNotFoundException);
        }
    }
}
