package com.marcoantdev.service;

import com.marcoantdev.core.usecase.ports.ExpenseProcessor;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class CsvProcessorService implements ExpenseProcessor {

    @Override
    public Map<String, Double> process(InputStream inputStream, String password) {
        try {
            Map<String, Double> expenses = new HashMap<>();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");

                String description = columns[0].trim();
                Double amount = Double.parseDouble(columns[1].trim());

                expenses.merge(description, amount, Double::sum);
            }

            return expenses;
        } catch (Exception e) {
            throw new RuntimeException("Error processing CSV file: " + e.getMessage(), e);
        }
    }
}
