package com.marcoantdev.hexagonalarchitecture.mappers;

import com.marcoantdev.hexagonalarchitecture.domain.models.ExpenseEntity;
import com.marcoantdev.hexagonalarchitecture.dtos.ExpenseDto;

public class ExpenseMapper {
  public static ExpenseDto toDto(final ExpenseEntity expenseEntity) {
    return ExpenseDto.builder()
        .description(expenseEntity.getDescription())
        .amount(expenseEntity.getAmount())
        .category(expenseEntity.getCategory())
        .date(expenseEntity.getDate())
        .build();
  }

  public static ExpenseEntity toEntity(final ExpenseDto expenseDTO) {
    return ExpenseEntity.builder()
        .description(expenseDTO.getDescription())
        .amount(expenseDTO.getAmount())
        .category(expenseDTO.getCategory())
        .date(expenseDTO.getDate())
        .build();
  }
}
