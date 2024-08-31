package com.marcoantdev.hexagonalarchitecture.domain.models;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseEntity extends PanacheMongoEntity {

  private String description;
  private double amount;
  private String category;
  private String date;
}
