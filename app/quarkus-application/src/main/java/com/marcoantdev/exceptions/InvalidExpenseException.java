package com.marcoantdev.exceptions;

public class InvalidExpenseException extends SmartExpenseException {
  public InvalidExpenseException(String message) {
    super(message);
  }
}
