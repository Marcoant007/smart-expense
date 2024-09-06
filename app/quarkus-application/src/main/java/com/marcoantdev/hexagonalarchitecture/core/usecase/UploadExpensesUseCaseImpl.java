package com.marcoantdev.hexagonalarchitecture.core.usecase;

import com.marcoantdev.hexagonalarchitecture.core.usecase.ports.UploadExpensesUseCase;
import com.marcoantdev.hexagonalarchitecture.domain.models.ExpenseEntity;
import com.marcoantdev.hexagonalarchitecture.domain.repository.ExpenseRepository;
import com.marcoantdev.hexagonalarchitecture.utils.ProcessPdfUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.io.InputStream;
import java.time.LocalDate;
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
      ExpenseEntity expense = new ExpenseEntity(description, amount, "Importado", LocalDate.now().toString());
      // expenseRepository.persist(expense);
    });

    return groupedExpenses;
  }
}
