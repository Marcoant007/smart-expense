package com.marcoantdev.core.usecase.ports;

import com.marcoantdev.dtos.ExpenseDto;
import java.util.List;

public interface ListExpenseUseCase {
  List<ExpenseDto> listAllExpenses();
}
