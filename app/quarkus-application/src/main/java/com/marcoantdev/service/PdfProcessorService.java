package com.marcoantdev.service;

import com.marcoantdev.core.usecase.ports.ExpenseProcessor;
import com.marcoantdev.handler.ExtractTextHandler;
import com.marcoantdev.handler.LoadHandler;
import com.marcoantdev.handler.categorize.pdf.CategorizePdfExpensesHandler;
import com.marcoantdev.handler.context.PdfContext;
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

            LoadHandler loadHandler = new LoadHandler();
            ExtractTextHandler extractTextHandler = new ExtractTextHandler();
            CategorizePdfExpensesHandler categorizeHandler = new CategorizePdfExpensesHandler();

            loadHandler.setNext(extractTextHandler).setNext(categorizeHandler);
            loadHandler.handle(context);

            return context.getExpenses(); // Return cate
        } catch (Exception e) {
            throw new RuntimeException("Error processing PDF: " + e.getMessage(), e);
        }
    }
}
