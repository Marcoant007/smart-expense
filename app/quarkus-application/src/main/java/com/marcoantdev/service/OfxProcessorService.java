package com.marcoantdev.service;

import com.marcoantdev.core.usecase.ports.ExpenseProcessor;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class OfxProcessorService implements ExpenseProcessor {

    @Override
    public Map<String, Double> process(InputStream inputStream, String password) {
        try {
            Map<String, Double> expenses = new HashMap<>();

            List<String> ofxLines = new BufferedReader(new InputStreamReader(inputStream))
                    .lines()
                    .toList();

            for (String line : ofxLines) {
                if (line.contains("<STMTTRN>")) {
                    String description = extractTagValue(ofxLines, "<MEMO>");
                    String amountValue = extractTagValue(ofxLines, "<TRNAMT>");
                    Double amount = Double.parseDouble(amountValue);
                    expenses.merge(description, amount, Double::sum);
                }
            }

            return expenses;
        } catch (Exception e) {
            throw new RuntimeException("Error processing OFX file: " + e.getMessage(), e);
        }
    }

    private String extractTagValue(List<String> lines, String tagName) {
        return lines.stream()
                .filter(line -> line.contains(tagName))
                .map(line -> line.replace(tagName, "").replace("</" + tagName.substring(1), "").trim())
                .findFirst()
                .orElse("");
    }
}
