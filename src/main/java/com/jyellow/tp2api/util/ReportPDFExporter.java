package com.jyellow.tp2api.util;

import java.awt.Color;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyellow.tp2api.model.Patient;
import com.jyellow.tp2api.model.Psychologist;
import com.jyellow.tp2api.model.Report;
import com.jyellow.tp2api.repository.ReportRepository;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class ReportPDFExporter {

	@Autowired
	ReportRepository reportRepository;

	private void writeTablePatient(Patient patient, PdfPTable table) throws DocumentException, IOException {
		PdfPCell cell = new PdfPCell();
		BaseFont customfont = BaseFont.createFont("static/ROCK.TTF", BaseFont.CP1252, BaseFont.EMBEDDED);
		Font font = new Font(customfont);

		cell.setPhrase(new Phrase("Paciente", font));
		cell.setBackgroundColor(Color.GRAY);
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setPhrase(new Phrase(patient.getLastNames() + ", " + patient.getNames(), font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("DNI", font));
		cell.setBackgroundColor(Color.GRAY);
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setPhrase(new Phrase(patient.getUserLogin().getDni(), font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Fecha de nacimiento", font));
		cell.setBackgroundColor(Color.GRAY);
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setPhrase(new Phrase(patient.getBirthday(), font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Teléfono", font));
		cell.setBackgroundColor(Color.GRAY);
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setPhrase(new Phrase(patient.getPhone(), font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Correo electrónico", font));
		cell.setBackgroundColor(Color.GRAY);
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setPhrase(new Phrase(patient.getEmail(), font));
		table.addCell(cell);
	}

	private void writeTableReport(Report report, PdfPTable table) throws DocumentException, IOException {
		PdfPCell cell = new PdfPCell();
		BaseFont customfont = BaseFont.createFont("static/ROCK.TTF", BaseFont.CP1252, BaseFont.EMBEDDED);
		Font font = new Font(customfont);

		cell.setPhrase(new Phrase("Fecha de elaboración", font));
		cell.setBackgroundColor(Color.GRAY);
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setPhrase(new Phrase(report.getDate(), font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Hora de elaboración", font));
		cell.setBackgroundColor(Color.GRAY);
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setPhrase(new Phrase(report.getHour(), font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Tipo", font));
		cell.setBackgroundColor(Color.GRAY);
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setPhrase(new Phrase(report.getType(), font));
		table.addCell(cell);

	}

	private void writeTablePsychologist(Psychologist psychologist, PdfPTable table)
			throws DocumentException, IOException {
		PdfPCell cell = new PdfPCell();
		BaseFont customfont = BaseFont.createFont("static/ROCK.TTF", BaseFont.CP1252, BaseFont.EMBEDDED);
		Font font = new Font(customfont);

		cell.setPhrase(new Phrase("Psicólogo", font));
		cell.setBackgroundColor(Color.GRAY);
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setPhrase(new Phrase(psychologist.getLastNames() + ", " + psychologist.getNames(), font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("DNI", font));
		cell.setBackgroundColor(Color.GRAY);
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setPhrase(new Phrase(psychologist.getUserLogin().getDni(), font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("C.Ps.P", font));
		cell.setBackgroundColor(Color.GRAY);
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setPhrase(new Phrase(psychologist.getCpsp(), font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Teléfono", font));
		cell.setBackgroundColor(Color.GRAY);
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setPhrase(new Phrase(psychologist.getPhone(), font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Correo electrónico", font));
		cell.setBackgroundColor(Color.GRAY);
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setPhrase(new Phrase(psychologist.getEmail(), font));
		table.addCell(cell);
	}

	public void export(HttpServletResponse response, int idReport) throws DocumentException, IOException {
		Report report = reportRepository.findById(idReport).get();
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		BaseFont customfont = BaseFont.createFont("static/rockb.ttf", BaseFont.CP1252, BaseFont.EMBEDDED);
		Font font = new Font(customfont);
		font.setSize(18);

		Paragraph p = new Paragraph("Informe Psicológico", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(p);
		document.add(Chunk.NEWLINE);

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 4f, 8f });
		writeTableReport(report, table);
		document.add(table);

		table = new PdfPTable(2);
		table.setWidthPercentage(100f);
		table.setSpacingBefore(10);
		table.setWidths(new float[] { 4f, 8f });
		writeTablePatient(report.getPatient(), table);
		document.add(table);

		table = new PdfPTable(2);
		table.setWidthPercentage(100f);
		table.setSpacingBefore(10);
		table.setWidths(new float[] { 4f, 8f });
		writeTablePsychologist(report.getPsychologist(), table);
		document.add(table);

		document.add(Chunk.NEWLINE);
		customfont = BaseFont.createFont("static/ROCK.TTF", BaseFont.CP1252, BaseFont.EMBEDDED);
		font = new Font(customfont);
		p = new Paragraph(report.getDescription(), font);
		p.setAlignment(Paragraph.ALIGN_JUSTIFIED);
		document.add(p);

		document.close();

	}
}
