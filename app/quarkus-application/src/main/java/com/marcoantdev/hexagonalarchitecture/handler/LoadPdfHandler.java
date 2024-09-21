package com.marcoantdev.hexagonalarchitecture.handler;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.io.InputStream;

public class LoadPdfHandler extends BasePdfHandler {
    private static final Logger LOGGER = Logger.getLogger(LoadPdfHandler.class);

    @Override
    public void handle(PdfContext context) throws Exception {
        if (context.getInputStream() == null || context.getInputStream().available() == 0) {
            throw new IOException("File cannot be null");
        }
        validatePassword(context.getInputStream(), context.getPassword());
        context.getInputStream().reset();

        PDDocument document = PDDocument.load(context.getInputStream(), context.getPassword());
        LOGGER.info("✅ Document Validate Success");
        context.setDocument(document);
        next(context);
    }

    private void validatePassword(InputStream inputStream, String password) throws Exception {
        try (PDDocument document = PDDocument.load(inputStream, password)) {
            LOGGER.info("✅ Validate Password Success");
        } catch (InvalidPasswordException e) {
            LOGGER.error("Invalid Password");
            throw new Exception(e);
        }
    }
}
