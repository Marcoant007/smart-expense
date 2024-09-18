package com.marcoantdev.hexagonalarchitecture.core.usecase.ports;

import com.marcoantdev.hexagonalarchitecture.dtos.ExpenseDTO;

public interface CreateExpenseUseCase {
    ExpenseDTO createExpense(ExpenseDTO expenseDTO);
}
