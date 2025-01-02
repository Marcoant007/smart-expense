package com.marcoantdev.handler.categorize.pdf;

import com.marcoantdev.handler.BaseHandler;
import com.marcoantdev.handler.context.PdfContext;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CategorizePdfExpensesHandler extends BaseHandler<PdfContext> {
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
  public void handle(PdfContext context) throws Exception {
    String text = context.getExtractedText();
    if (text == null || text.isEmpty()) {
      throw new IllegalArgumentException("Texto extraído do PDF está vazio.");
    }

    log.info("Extracted text: {}", text);

    Pattern pattern = Pattern.compile(
        "(\\d{2}/\\d{2}/\\d{4})\\s+([A-Za-zÀ-ÿ0-9\\s]+?)\\s+-\\sR\\$\\s([0-9]+,[0-9]{2})"
    );
    Matcher matcher = pattern.matcher(text);

    while (matcher.find()) {
      String description = matcher.group(2).trim();
      String rawAmount = matcher.group(3).replace(",", ".");
      double amount = Double.parseDouble(rawAmount);

      String category = identifyCategory(description);

      String key = String.format("%s | %s", description, matcher.group(1));
      context.getExpenses().merge(key, amount, Double::sum);

      log.info("Transaction: {} | Amount: {} | Category: {}", description, amount, category);
    }

    log.info("✅ Categorization for PDF completed.");
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
