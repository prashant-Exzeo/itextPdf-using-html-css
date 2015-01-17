package com.knightRider.itextPdf_using_html_css;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

/**
 * @author prashant
 * 
 */
public class App {
	public static void main(String[] args) throws IOException, DocumentException {
		// Create a buffer to hold the cleaned up HTML
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		// Clean up the HTML to be well formed
		HtmlCleaner cleaner = new HtmlCleaner();
		CleanerProperties props = cleaner.getProperties();
		TagNode node = cleaner.clean(App.class.getClassLoader().getResource(
				"Report.html"));
		// Instead of writing to System.out we now write to the ByteArray buffer
		new PrettyXmlSerializer(props).writeToStream(node, out);

		// Create the PDF
		ITextRenderer renderer = new ITextRenderer();

		// String content = out.toString();
		// System.out.println(content);

		renderer.setDocument(new File(App.class.getClassLoader()
				.getResource("content.txt").getFile()));

		// renderer.setDocumentFromString(new String(out.toByteArray()));

		renderer.layout();
		OutputStream outputStream = new FileOutputStream("HTMLasPDF.pdf");
		renderer.createPDF(outputStream);

		// Finishing up
		renderer.finishPDF();
		out.flush();
		out.close();
	}
}
