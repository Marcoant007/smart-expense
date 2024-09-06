package com.marcoantdev.hexagonalarchitecture.core.usecase.ports;

import java.io.InputStream;
import java.util.Map;

public interface UploadExpensesUseCase {
  Map<String, Double> uploadExpenses(InputStream inputStream);
}
