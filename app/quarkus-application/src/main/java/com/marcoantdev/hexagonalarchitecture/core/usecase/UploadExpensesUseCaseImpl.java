package com.marcoantdev.hexagonalarchitecture.core.usecase;

import com.marcoantdev.hexagonalarchitecture.core.usecase.ports.UploadExpensesUseCase;
import com.marcoantdev.hexagonalarchitecture.domain.models.ExpenseEntity;
import com.marcoantdev.hexagonalarchitecture.domain.repository.ExpenseRepository;
import com.marcoantdev.hexagonalarchitecture.utils.ProcessPdfUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.io.InputStream;
import java.util.Map;

@ApplicationScoped
public class UploadExpensesUseCaseImpl implements UploadExpensesUseCase {

  @Inject
  ExpenseRepository expenseRepository;

  @Inject
  ProcessPdfUtils processPdfUtils;

  @Override
  @Transactional
  public Map<String, Double> uploadExpenses(InputStream inputStream) {
    Map<String, Double> groupedExpenses = processPdfUtils.processPdf(inputStream);

    groupedExpenses.forEach((description, amount) -> {
      ExpenseEntity expense = ExpenseEntity.builder()
          .description("Compra no mercado")
          .amount(150.0)
          .category("Alimentação")
          .date("2024-09-01")
          .build();
      expenseRepository.persist(expense);
    });

    return groupedExpenses;
  }
}
