package com.marcoantdev.core.usecase.ports;

import com.marcoantdev.dtos.ExpenseDto;

public interface CreateExpenseUseCase {
  ExpenseDto createExpense(ExpenseDto expenseDTO);
}
