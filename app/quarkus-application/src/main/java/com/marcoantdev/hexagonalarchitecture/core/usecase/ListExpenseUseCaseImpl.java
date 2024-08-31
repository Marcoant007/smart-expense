package com.marcoantdev.hexagonalarchitecture.core.usecase;

import com.marcoantdev.hexagonalarchitecture.core.usecase.ports.ListExpenseUseCase;
import com.marcoantdev.hexagonalarchitecture.domain.models.ExpenseEntity;
import com.marcoantdev.hexagonalarchitecture.domain.repository.ExpenseRepository;
import com.marcoantdev.hexagonalarchitecture.dtos.ExpenseResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ListExpenseUseCaseImpl implements ListExpenseUseCase {

  @Inject
  ExpenseRepository expenseRepository;

  @Override
  public List<ExpenseResponseDTO> listAllExpenses() {
    return expenseRepository.listAll().stream()
        .map(ExpenseResponseDTO::fromEntity)
        .collect(Collectors.toList());
  }
}
