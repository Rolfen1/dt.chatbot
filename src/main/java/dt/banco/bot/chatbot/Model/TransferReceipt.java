package dt.banco.bot.chatbot.Model;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransferReceipt {
    private Person from;
    private Person to;
    private Integer amount;

    public TransferReceipt() {
    }

    public TransferReceipt(Person from, Person to, Integer amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public Person getFrom() {
        return from;
    }

    public void setFrom(Person from) {
        this.from = from;
    }

    public Person getTo() {
        return to;
    }

    public void setTo(Person to) {
        this.to = to;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public ByteArrayInputStream toPdf() {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(60);
            table.setWidths(new int[]{3, 3});

            // Amount
            table.addCell(CreateCustomCell("Monto:"));
            table.addCell(CreateCustomCell(amount.toString()));

            // From
            table.addCell(CreateCustomCell("Desde:"));
            table.addCell(CreateCustomCell(from.getName()));
            table.addCell(CreateCustomCell(""));
            table.addCell(CreateCustomCell(from.getAccountNumber().toString()));

            // To
            table.addCell(CreateCustomCell("Hacia:"));
            table.addCell(CreateCustomCell(to.getName()));
            table.addCell(CreateCustomCell(""));
            table.addCell(CreateCustomCell(to.getAccountNumber().toString()));

            // Other data
            table.addCell(CreateCustomCell("Fecha:"));
            table.addCell(CreateCustomCell(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))));

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);

            document.close();
        } catch (Exception e) {
            return null;
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    private PdfPCell CreateCustomCell(String content) {
        PdfPCell cell = new PdfPCell(new Phrase(content));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        return cell;
    }
}
