package com.marcoantdev.hexagonalarchitecture.domain.models;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ExpenseEntity extends PanacheMongoEntity {

  private final String description;
  private final double amount;
  private final String category;
  private final String date;

  @Builder
  private ExpenseEntity(final String description, final double amount, final String category, final String date) {
    this.description = Objects.requireNonNull(description, "Description cannot be null");
    this.amount = amount;
    this.category = Objects.requireNonNull(category, "Category cannot be null");
    this.date = Objects.requireNonNull(date, "Date cannot be null");
  }
}
