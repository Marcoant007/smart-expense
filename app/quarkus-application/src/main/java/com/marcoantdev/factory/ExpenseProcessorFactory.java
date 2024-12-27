package com.marcoantdev.factory;

import com.marcoantdev.core.usecase.ports.ExpenseProcessor;
import com.marcoantdev.service.CsvProcessorService;
import com.marcoantdev.service.OfxProcessorService;
import com.marcoantdev.service.PdfProcessorService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ExpenseProcessorFactory {
    @Inject
    private PdfProcessorService pdfProcessor;

    @Inject
    private OfxProcessorService ofxProcessor;

    @Inject
    private CsvProcessorService csvProcessor;

    public ExpenseProcessor getProcessor(String format) {
        switch (format.toLowerCase()) {
            case "pdf":
                return pdfProcessor;
            case "ofx":
                return ofxProcessor;
            case "csv":
                return csvProcessor;
            default:
                throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }
}
