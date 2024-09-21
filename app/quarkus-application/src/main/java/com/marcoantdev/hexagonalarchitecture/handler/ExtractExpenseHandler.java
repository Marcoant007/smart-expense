package com.marcoantdev.hexagonalarchitecture.handler;

import org.jboss.logging.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractExpenseHandler extends BasePdfHandler {
    private static final Logger LOGGER = Logger.getLogger(LoadPdfHandler.class);

    @Override
    public void handle(PdfContext context) throws Exception {
        String text = context.getExtractedText();
        Map<String, Double> expensesMap = new HashMap<>();
        Pattern pattern = Pattern.compile("([A-Za-zÀ-ÿ\\s]+?)\\s*(Parcela.*?\\d+\\sde\\s\\d+)?\\s*R\\$\\s([0-9]+,\\d{2})");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String expenseName = matcher.group(1).trim();
            Double value = Double.parseDouble(matcher.group(3).replace(",", "."));

            expensesMap.merge(expenseName, value, Double::sum);
        }

        LOGGER.info("✅ Extract Expense Success");
        context.setExpenses(expensesMap);
        next(context);
    }
}
