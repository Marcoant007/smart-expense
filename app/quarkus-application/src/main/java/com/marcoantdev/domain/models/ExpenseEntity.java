package com.marcoantdev.domain.models;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.*;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@MongoEntity(collection = "expense")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseEntity {
    private ObjectId id;

    @BsonProperty("description")
    private String description;

    @BsonProperty("amount")
    private double amount;

    @BsonProperty("category")
    private String category;

    @BsonProperty("date")
    private LocalDate date;

    @BsonProperty("created_at")
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @BsonProperty("updated_at")
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Builder.Default
    private int revision = 1;
}
