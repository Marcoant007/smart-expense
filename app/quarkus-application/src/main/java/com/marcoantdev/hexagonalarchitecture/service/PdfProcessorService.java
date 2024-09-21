package com.marcoantdev.hexagonalarchitecture.service;

import com.marcoantdev.hexagonalarchitecture.handler.ExtractExpenseHandler;
import com.marcoantdev.hexagonalarchitecture.handler.ExtractTextHandler;
import com.marcoantdev.hexagonalarchitecture.handler.LoadPdfHandler;
import com.marcoantdev.hexagonalarchitecture.handler.PdfContext;
import com.marcoantdev.hexagonalarchitecture.handler.port.PdfProcessHandlerPort;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;


@ApplicationScoped
public class PdfProcessorService {

    public Map<String, Double> processPdf(InputStream inputStream, String password) throws Exception {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        inputStream.transferTo(stream);
        InputStream inputStreamClone = new ByteArrayInputStream(stream.toByteArray());

        PdfContext context = new PdfContext();
        context.setInputStream(inputStreamClone);
        context.setPassword(password);

        PdfProcessHandlerPort loadPdf = new LoadPdfHandler();
        PdfProcessHandlerPort extractText = new ExtractTextHandler();
        PdfProcessHandlerPort extractExpenses = new ExtractExpenseHandler();

        loadPdf.setNext(extractText).setNext(extractExpenses);
        loadPdf.handle(context);
        return context.getExpenses();
    }
}
