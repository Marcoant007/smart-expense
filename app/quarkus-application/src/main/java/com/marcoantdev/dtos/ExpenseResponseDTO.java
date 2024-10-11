package com.marcoantdev.dtos;

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
}
