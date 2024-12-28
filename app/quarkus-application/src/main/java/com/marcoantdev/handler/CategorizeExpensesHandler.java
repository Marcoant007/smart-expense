package com.marcoantdev.handler;

import com.marcoantdev.dtos.ExpenseDto;
import com.marcoantdev.handler.context.BaseContext;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class CategorizeExpensesHandler<T extends BaseContext> extends BaseHandler<T> {
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
    public void handle(T context) throws Exception {
        List<ExpenseDto> transactions = new ArrayList<>();

        context.getExpenses().forEach((description, amount) -> {
            String category = identifyCategory(description);
            ExpenseDto transaction = ExpenseDto.builder()
                    .description(description)
                    .amount(amount)
                    .category(category)
                    .date(LocalDate.now())
                    .build();

            transactions.add(transaction);
            log.info(String.format("Categorized: %s -> %s (%.2f)", description, category, amount));
        });

        context.setTransactions(transactions);
        log.info("✅ Categorization completed successfully.");
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
