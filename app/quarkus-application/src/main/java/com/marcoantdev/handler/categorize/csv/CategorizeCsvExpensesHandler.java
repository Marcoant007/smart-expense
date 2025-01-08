package com.marcoantdev.handler.categorize.csv;

import com.marcoantdev.handler.BaseHandler;
import com.marcoantdev.handler.context.CsvContext;
import com.marcoantdev.utils.CsvUtils;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ApplicationScoped
public class CategorizeCsvExpensesHandler extends BaseHandler<CsvContext> {

    @Override
    public void handle(CsvContext context) throws Exception {
        List<String[]> rows = context.getRows();
        if (rows == null || rows.isEmpty()) {
            throw new IllegalArgumentException("CSV rows cannot be null or empty.");
        }

        Map<String, Double> expenses = new HashMap<>();

        for (String[] row : rows) {
            if (row.length < 5) {
                log.warn("Skipping invalid CSV row: {}", String.join(",", row));
                continue;
            }

            String description = row[1].trim();
            String category = row[2].trim();
            String rawValue = row[4].trim();

            double amount;
            try {
                String cleanedValue = CsvUtils.cleanMonetaryValue(rawValue);
                amount = CsvUtils.parseDouble(cleanedValue);
            } catch (Exception e) {
                log.warn("Skipping invalid value for row: {}", String.join(",", row));
                continue;
            }

            String key = category + " - " + description;
            expenses.merge(key, amount, Double::sum);

            log.info(String.format("Processed: %s | %.2f | %s", description, amount, category));
        }

        context.setExpenses(expenses);
        log.info("✅ CSV Categorization completed successfully.");
    }
}

