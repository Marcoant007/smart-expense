package com.marcoantdev.handler.categorize.ofx;

import com.marcoantdev.handler.BaseHandler;
import com.marcoantdev.handler.context.OfxContext;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public class CategorizeOfxExpensesHandler extends BaseHandler<OfxContext> {
    private static final Map<String, String> CATEGORY_MAP = Map.of(
            "MERCADOLIVRE", "Compras",
            "HORTIFRUTI", "Alimentação",
            "IFOOD", "Delivery",
            "UBER", "Transporte",
            "SUPERMARKET", "Alimentação",
            "AMAZON", "Compras",
            "PIX", "Transferência",
            "GOOGLE", "Assinaturas",
            "ALMYR", "Alimentação",
            "IFD", "Alimentação"
    );

    @Override
    public void handle(OfxContext context) throws Exception {
        for (String line : context.getLines()) {
            if (line.contains("<STMTTRN>")) {
                String description = extractTagValue(context.getLines(), "<MEMO>");
                String rawAmount = extractTagValue(context.getLines(), "<TRNAMT>");
                double amount = Double.parseDouble(rawAmount);

                String category = identifyCategory(description);
                context.getExpenses().merge(category, amount, Double::sum);
            }
        }

        log.info("✅ Categorization for OFX completed.");
        next(context);
    }

    private String extractTagValue(List<String> lines, String tag) {
        return lines.stream()
                .filter(line -> line.contains(tag))
                .map(line -> line.replace(tag, "").replace("</" + tag.substring(1), "").trim())
                .findFirst()
                .orElse("");
    }

    private String identifyCategory(String description) {
        return CATEGORY_MAP.entrySet().stream()
                .filter(entry -> description.toUpperCase().contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse("Outros");
    }
}
