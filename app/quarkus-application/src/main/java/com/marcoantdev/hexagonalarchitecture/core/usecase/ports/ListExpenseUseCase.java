package com.marcoantdev.hexagonalarchitecture.core.usecase.ports;

import com.marcoantdev.hexagonalarchitecture.dtos.ExpenseDto;
import java.util.List;

public interface ListExpenseUseCase {
  List<ExpenseDto> listAllExpenses();
}
