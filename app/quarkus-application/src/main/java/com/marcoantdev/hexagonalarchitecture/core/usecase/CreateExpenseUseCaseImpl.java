package com.marcoantdev.hexagonalarchitecture.core.usecase;

import com.marcoantdev.hexagonalarchitecture.core.usecase.ports.CreateExpenseUseCase;
import com.marcoantdev.hexagonalarchitecture.domain.models.ExpenseEntity;
import com.marcoantdev.hexagonalarchitecture.domain.repository.ExpenseRepository;
import com.marcoantdev.hexagonalarchitecture.dtos.ExpenseDTO;
import com.marcoantdev.hexagonalarchitecture.dtos.ExpenseResponseDTO;
import com.marcoantdev.hexagonalarchitecture.mappers.ExpenseMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CreateExpenseUseCaseImpl implements CreateExpenseUseCase {

  @Inject
  ExpenseRepository expenseRepository;

  @Override
  @Transactional
  public ExpenseResponseDTO createExpense(ExpenseDTO expenseDTO) {
    ExpenseEntity expense = ExpenseMapper.toEntity(expenseDTO);
    expenseRepository.persist(expense);
    return ExpenseResponseDTO.fromEntity(expense);
  }
}
