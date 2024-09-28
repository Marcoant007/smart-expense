package com.marcoantdev.hexagonalarchitecture.core.usecase.ports;

import com.marcoantdev.hexagonalarchitecture.dtos.ExpenseDto;

public interface CreateExpenseUseCase {
  ExpenseDto createExpense(ExpenseDto expenseDTO);
}
