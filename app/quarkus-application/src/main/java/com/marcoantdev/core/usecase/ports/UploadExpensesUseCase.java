package com.marcoantdev.core.usecase.ports;

import com.marcoantdev.dtos.ExpenseDto;
import com.marcoantdev.dtos.ExpenseRequestDto;

import java.util.List;

public interface UploadExpensesUseCase {
    List<ExpenseDto> uploadExpenses(ExpenseRequestDto expenseRequestDto, String password) throws Exception;
}
