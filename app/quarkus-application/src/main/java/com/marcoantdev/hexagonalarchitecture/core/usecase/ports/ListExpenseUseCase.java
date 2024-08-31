package com.marcoantdev.hexagonalarchitecture.core.usecase.ports;

import com.marcoantdev.hexagonalarchitecture.domain.models.ExpenseEntity;
import com.marcoantdev.hexagonalarchitecture.dtos.ExpenseResponseDTO;
import java.util.List;

public interface ListExpenseUseCase {
  List<ExpenseResponseDTO> listAllExpenses();
}
