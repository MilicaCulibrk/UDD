package com.milicaculibrk.udd.handler;

import com.milicaculibrk.udd.model.IndexUnit;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.IOException;

public class PDFHandler{

	public IndexUnit getIndexUnit(File file) {
		IndexUnit retVal = new IndexUnit();

		try {

			//text extraction
			PDFParser parser = new PDFParser(new RandomAccessFile(file, "r"));
			parser.parse();
			String text = getText(parser);
			retVal.setText(text);

			// metadata extraction
			PDDocument pdf = parser.getPDDocument();
			PDDocumentInformation information = pdf.getDocumentInformation();

//			retVal.setTitle("" + information.getTitle());
//			retVal.setKeywords("" + information.getKeywords());
			retVal.setFilename(file.getCanonicalPath());
			
			pdf.close();
		} catch (IOException e) {
			System.out.println("Error while converting document to pdf");
		}

		return retVal;
	}

	public String getText(File file) {
		try {
			PDFParser parser = new PDFParser(new RandomAccessFile(file, "r"));
			parser.parse();
			PDFTextStripper textStripper = new PDFTextStripper();
			String text = textStripper.getText(parser.getPDDocument());
			return text;
		} catch (IOException e) {
			System.out.println("Error while converting document to pdf");
		}
		return null;
	}
	
	public String getText(PDFParser parser) {
		try {
			PDFTextStripper textStripper = new PDFTextStripper();
			String text = textStripper.getText(parser.getPDDocument());
			return text;
		} catch (IOException e) {
			System.out.println("Error while converting document to pdf");
		}
		return null;
	}

}
