package com.marcoantdev.handler;

import com.marcoantdev.handler.context.PdfContext;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class ExtractExpenseHandler extends BaseHandler<PdfContext> {
    private static final Map<String, String> CATEGORY_MAP = Map.of(
            "MERCADO", "Alimentação",
            "UBER", "Transporte",
            "IFOOD", "Delivery",
            "PIX", "Transferência",
            "AMAZON", "Entretenimento",
            "HORTIFRUTI", "Alimentação",
            "FARMACIA", "Saúde"
    );

    @Override
    public void handle(PdfContext context) throws Exception {
        String text = context.getExtractedText();
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Texto extraído do PDF está vazio.");
        }

        Map<String, Double> categorizedTotals = new HashMap<>();

        Pattern pattern = Pattern.compile(
                "(\\d{2}\\sde\\s\\w+\\.\\s\\d{4})\\s+([A-Za-zÀ-ÿ0-9\\s]+?)\\s+-\\sR\\$\\s([0-9]+,[0-9]{2})"
        );
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String date = matcher.group(1).trim();
            String description = matcher.group(2).trim();
            String rawAmount = matcher.group(3).replace(",", ".");
            double amount = Double.parseDouble(rawAmount);

            String category = identifyCategory(description);

            categorizedTotals.merge(category, amount, Double::sum);

            log.info(String.format("Movimentação capturada: %s | %s | %.2f", date, description, amount));
        }

        if (categorizedTotals.isEmpty()) {
            log.warn("Nenhuma movimentação encontrada. Verifique o formato do texto.");
        }

        log.info("✅ Extração concluída com sucesso.");
        context.setExpenses(categorizedTotals);
        next(context);
    }

    private String identifyCategory(String description) {
        return CATEGORY_MAP.entrySet().stream()
                .filter(entry -> description.toUpperCase().contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse("Outros");
    }
}
