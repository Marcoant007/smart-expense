package com.marcoantdev.service;

import com.marcoantdev.core.usecase.ports.ExpenseProcessor;
import com.marcoantdev.handler.categorize.csv.CategorizeCsvExpensesHandler;
import com.marcoantdev.handler.context.CsvContext;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class CsvProcessorService implements ExpenseProcessor {

    @Override
    public Map<String, Double> process(InputStream inputStream, String password) {
        try {
            if (inputStream == null || inputStream.available() == 0) {
                throw new IllegalArgumentException("CSV InputStream cannot be null or empty.");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            List<String[]> rows = new ArrayList<>();
            String line;

            reader.readLine();

            while ((line = reader.readLine()) != null) {
                rows.add(line.split(","));
            }

            CsvContext context = new CsvContext();
            context.setRows(rows);

            CategorizeCsvExpensesHandler categorizeHandler = new CategorizeCsvExpensesHandler();
            categorizeHandler.handle(context);

            return context.getExpenses();
        } catch (Exception e) {
            throw new RuntimeException("Error processing CSV file: " + e.getMessage(), e);
        }
    }
}
