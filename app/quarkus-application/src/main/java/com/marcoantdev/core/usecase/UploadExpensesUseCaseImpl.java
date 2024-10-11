package com.marcoantdev.core.usecase;

import com.marcoantdev.core.usecase.ports.UploadExpensesUseCase;
import com.marcoantdev.domain.enums.ExpenseCategoryEnum;
import com.marcoantdev.domain.models.ExpenseEntity;
import com.marcoantdev.domain.repository.ExpenseRepository;
import com.marcoantdev.dtos.ExpenseRequestDto;
import com.marcoantdev.service.PdfProcessorService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Map;

@ApplicationScoped
public class UploadExpensesUseCaseImpl implements UploadExpensesUseCase {

  @Inject
  ExpenseRepository expenseRepository;

  @Inject
  PdfProcessorService processPdfUtils;

  @Override
  public Map<String, Double> uploadExpenses(ExpenseRequestDto expenseRequestDto, String password) throws Exception {
    InputStream fileStream = expenseRequestDto.getFile();

    Map<String, Double> groupedExpenses = processPdfUtils.processPdf(fileStream, password);

    groupedExpenses.forEach((description, amount) -> {
      ExpenseEntity expense = ExpenseEntity.builder()
          .description(description)
          .amount(amount)
          .category(determineCategory(description))
          .date(LocalDateTime.now())
          .createdAt(LocalDateTime.now())
          .updatedAt(LocalDateTime.now())
          .build();

      expenseRepository.persist(expense);
    });

    return groupedExpenses;
  }

  private String determineCategory(String description) {
    return ExpenseCategoryEnum.fromDescription(description);
  }
}
