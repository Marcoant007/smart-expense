package com.marcoantdev.core.usecase;

import com.marcoantdev.core.usecase.ports.ListExpenseUseCase;
import com.marcoantdev.domain.repository.ExpenseRepository;
import com.marcoantdev.dtos.ExpenseDto;
import com.marcoantdev.mappers.ExpenseMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ListExpenseUseCaseImpl implements ListExpenseUseCase {

  @Inject
  ExpenseRepository expenseRepository;

  @Override
  public List<ExpenseDto> listAllExpenses() {
    return expenseRepository.listAll().stream()
        .map(ExpenseMapper::toDto)
        .collect(Collectors.toList());
  }
}
