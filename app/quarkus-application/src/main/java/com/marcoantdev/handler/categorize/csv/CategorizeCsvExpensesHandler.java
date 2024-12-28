package com.marcoantdev.handler.categorize.csv;

import com.marcoantdev.handler.BaseHandler;
import com.marcoantdev.handler.context.CsvContext;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ApplicationScoped
public class CategorizeCsvExpensesHandler extends BaseHandler<CsvContext> {
    private static final Map<String, String> CATEGORY_MAP = Map.of(
            "MERCADO", "Alimentação",
            "UBER", "Transporte",
            "IFOOD", "Delivery",
            "PIX", "Transferência",
            "AMAZON", "Entretenimento",
            "HORTIFRUTI", "Alimentação",
            "FARMACIA", "Saúde",
            "IFD", "Alimentação",
            "HONEST", "Alimentação"
    );

    @Override
    public void handle(CsvContext context) throws Exception {
        List<String[]> rows = context.getRows();
        if (rows == null || rows.isEmpty()) {
            throw new IllegalArgumentException("CSV rows cannot be null or empty.");
        }

        Map<String, Double> expenses = new HashMap<>();

        for (String[] row : rows) {
            if (row.length < 3) {
                log.warn("Skipping invalid CSV row: " + String.join(",", row));
                continue;
            }

            String description = row[1].trim();
            double amount = Double.parseDouble(row[2].trim());

            String category = identifyCategory(description);

            expenses.merge(description, amount, Double::sum);
            log.info(String.format("Transaction: %s | %.2f | %s", description, amount, category));
        }

        context.setExpenses(expenses);
        log.info("✅ CSV Categorization completed successfully.");
    }

    private String identifyCategory(String description) {
        String category = CATEGORY_MAP.entrySet().stream()
                .filter(entry -> description.toUpperCase().contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse("Outros");

        if (category.equals("Outros")) {
            if (description.toUpperCase().contains("LANCHONETE") ||
                    description.toUpperCase().contains("PADARIA") ||
                    description.toUpperCase().contains("RESTAURANTE")) {
            }
        }

        return category;
    }
}
