package com.marcoantdev.core.usecase.ports;

import com.marcoantdev.dtos.ExpenseRequestDto;

import java.util.Map;

public interface UploadExpensesUseCase {
  Map<String, Double> uploadExpenses(ExpenseRequestDto expenseRequestDto, String password) throws Exception;
}
