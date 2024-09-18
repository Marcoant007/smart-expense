package com.marcoantdev.hexagonalarchitecture.domain.models;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseEntity extends PanacheMongoEntity {

    private String description;
    private double amount;
    private String category;
    private String date;
}
