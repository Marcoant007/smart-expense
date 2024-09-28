package com.marcoantdev.hexagonalarchitecture.core.usecase;

import com.marcoantdev.hexagonalarchitecture.core.usecase.ports.CreateExpenseUseCase;
import com.marcoantdev.hexagonalarchitecture.domain.models.ExpenseEntity;
import com.marcoantdev.hexagonalarchitecture.domain.repository.ExpenseRepository;
import com.marcoantdev.hexagonalarchitecture.dtos.ExpenseDto;
import com.marcoantdev.hexagonalarchitecture.exceptions.InvalidExpenseException;
import com.marcoantdev.hexagonalarchitecture.mappers.ExpenseMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateExpenseUseCaseImpl implements CreateExpenseUseCase {

  @Inject
  ExpenseRepository expenseRepository;

  @Override
  public ExpenseDto createExpense(ExpenseDto expenseDTO) {
    if (expenseDTO.getAmount() <= 0) {
      throw new InvalidExpenseException("Amount must be greater than zero");
    }
    ExpenseEntity expense = ExpenseMapper.toEntity(expenseDTO);
    expenseRepository.persist(expense);
    return ExpenseMapper.toDto(expense);
  }
}
