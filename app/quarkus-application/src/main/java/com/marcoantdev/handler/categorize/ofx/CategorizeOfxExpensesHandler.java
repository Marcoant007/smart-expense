package com.marcoantdev.handler.categorize.ofx;

import com.marcoantdev.handler.BaseHandler;
import com.marcoantdev.handler.context.OfxContext;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

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
    List<String> lines = context.getLines();

    for (int i = 0; i < lines.size(); i++) {
      if (lines.get(i).contains("<STMTTRN>")) {
        String description = extractTagValue(lines, i, "<MEMO>");
        String rawAmount = extractTagValue(lines, i, "<TRNAMT>");
        String date = extractTagValue(lines, i, "<DTPOSTED>");

        double amount = Double.parseDouble(rawAmount);
        String category = identifyCategory(description);

        String key = String.format("%s | %s", description, date);
        context.getExpenses().merge(key, amount, Double::sum);

        log.info(String.format("Transaction: %s | %.2f | %s | %s", description, amount, category, date));
      }
    }

    log.info("✅ OFX Categorization completed successfully.");
    next(context);
  }

  private String extractTagValue(List<String> lines, int currentIndex, String tag) {
    for (int i = currentIndex; i < lines.size(); i++) {
      if (lines.get(i).contains(tag)) {
        return lines.get(i)
            .replace(tag, "")
            .replace("</" + tag.substring(1), "")
            .trim();
      }
    }
    return "";
  }

  private String identifyCategory(String description) {
    return CATEGORY_MAP.entrySet().stream()
        .filter(entry -> description.toUpperCase().contains(entry.getKey()))
        .map(Map.Entry::getValue)
        .findFirst()
        .orElse("Outros");
  }
}
