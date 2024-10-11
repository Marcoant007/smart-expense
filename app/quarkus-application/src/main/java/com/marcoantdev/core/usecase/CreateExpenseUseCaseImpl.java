package com.marcoantdev.core.usecase;

import com.marcoantdev.core.usecase.ports.CreateExpenseUseCase;
import com.marcoantdev.domain.models.ExpenseEntity;
import com.marcoantdev.domain.repository.ExpenseRepository;
import com.marcoantdev.dtos.ExpenseDto;
import com.marcoantdev.exceptions.InvalidExpenseException;
import com.marcoantdev.mappers.ExpenseMapper;
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
