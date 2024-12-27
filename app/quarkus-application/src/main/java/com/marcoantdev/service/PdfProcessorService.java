package com.marcoantdev.service;

import com.marcoantdev.core.usecase.ports.ExpenseProcessor;
import com.marcoantdev.exceptions.PdfProcessingException;
import com.marcoantdev.handler.ExtractExpenseHandler;
import com.marcoantdev.handler.ExtractTextHandler;
import com.marcoantdev.handler.LoadHandler;
import com.marcoantdev.handler.context.PdfContext;
import com.marcoantdev.handler.port.ProcessHandlerPort;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;


@ApplicationScoped
public class PdfProcessorService implements ExpenseProcessor {

    @Override
    public Map<String, Double> process(InputStream inputStream, String password) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            inputStream.transferTo(stream);
            InputStream inputStreamClone = new ByteArrayInputStream(stream.toByteArray());

            PdfContext context = new PdfContext();
            context.setInputStream(inputStreamClone);
            context.setPassword(password);

            ProcessHandlerPort<PdfContext> loadPdf = new LoadHandler();
            ProcessHandlerPort<PdfContext> extractText = new ExtractTextHandler();
            ProcessHandlerPort<PdfContext> extractExpenses = new ExtractExpenseHandler();

            loadPdf.setNext(extractText).setNext(extractExpenses);
            loadPdf.handle(context);
            return context.getExpenses();
        } catch (Exception e) {
            throw new PdfProcessingException("An error occurred while processing the PDF: " + e.getMessage());
        }
    }
}
