package com.marcoantdev.handler;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.jboss.logging.Logger;

public class ExtractTextHandler extends BasePdfHandler {
  private static final Logger LOGGER = Logger.getLogger(LoadPdfHandler.class);

  @Override
  public void handle(PdfContext context) throws Exception {
    PDDocument document = context.getDocument();
    PDFTextStripper pdfStripper = new PDFTextStripper();
    String text = pdfStripper.getText(document);
    context.setExtractedText(text);

    LOGGER.info("✅ Text Extract Success");
    next(context);
  }
}