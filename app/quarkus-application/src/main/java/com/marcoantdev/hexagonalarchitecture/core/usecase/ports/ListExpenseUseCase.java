package com.marcoantdev.hexagonalarchitecture.core.usecase.ports;

import com.marcoantdev.hexagonalarchitecture.dtos.ExpenseDTO;

import java.util.List;

public interface ListExpenseUseCase {
    List<ExpenseDTO> listAllExpenses();
}
