package com.marcoantdev.hexagonalarchitecture.dtos;

import com.marcoantdev.hexagonalarchitecture.domain.models.ExpenseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseResponseDTO {
  private String id;
  private String description;
  private double amount;
  private String category;
  private String date;

  public static ExpenseResponseDTO fromEntity(ExpenseEntity entity) {
    return new ExpenseResponseDTO(
        entity.id.toString(),
        entity.getDescription(),
        entity.getAmount(),
        entity.getCategory(),
        entity.getDate()
    );
  }
}
