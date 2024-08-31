package com.marcoantdev.hexagonalarchitecture.core.usecase.ports;

import com.marcoantdev.hexagonalarchitecture.domain.models.ExpenseEntity;
import com.marcoantdev.hexagonalarchitecture.dtos.ExpenseDTO;
import com.marcoantdev.hexagonalarchitecture.dtos.ExpenseResponseDTO;

public interface CreateExpenseUseCase {
  ExpenseResponseDTO createExpense(ExpenseDTO expenseDTO);
}
