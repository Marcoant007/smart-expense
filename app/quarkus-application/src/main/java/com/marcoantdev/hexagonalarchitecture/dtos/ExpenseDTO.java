package com.marcoantdev.hexagonalarchitecture.dtos;

import com.marcoantdev.hexagonalarchitecture.domain.models.ExpenseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDTO {
  private String description;
  private double amount;
  private String category;
  private String date;

  public ExpenseEntity toEntity() {
    return new ExpenseEntity(description, amount, category, date);
  }

  public static ExpenseDTO fromEntity(ExpenseEntity entity) {
    return new ExpenseDTO(
        entity.getDescription(),
        entity.getAmount(),
        entity.getCategory(),
        entity.getDate()
    );
  }
}
