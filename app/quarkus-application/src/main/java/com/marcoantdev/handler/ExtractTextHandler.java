package com.marcoantdev.handler;

import com.marcoantdev.handler.context.PdfContext;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.jboss.logging.Logger;

public class ExtractTextHandler extends BaseHandler<PdfContext> {
    private static final Logger LOGGER = Logger.getLogger(LoadHandler.class);

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