package com.marcoantdev.hexagonalarchitecture.exceptions;

public class ExceptionFactory {
  public static InvalidExpenseException invalidExpense(String reason) {
    return new InvalidExpenseException("Invalid Expense: " + reason);
  }

  public static PdfProcessingException pdfProcessingError(String detail) {
    return new PdfProcessingException("Error processing PDF: " + detail);
  }
}
