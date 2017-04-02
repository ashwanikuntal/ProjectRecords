package org.projectdiaries.utility;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;

public class PdfGeneration {
	
	public void generatePdf() throws IOException, COSVisitorException {
		PDDocument document = new PDDocument();
		document.save("C:/PdfBox_Examples/my_doc.pdf");
		System.out.println("PDF created");  
		document.close();
	}

}
