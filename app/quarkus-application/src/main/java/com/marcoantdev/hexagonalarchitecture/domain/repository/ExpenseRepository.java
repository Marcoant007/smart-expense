package com.marcoantdev.hexagonalarchitecture.domain.repository;

import com.marcoantdev.hexagonalarchitecture.domain.models.ExpenseEntity;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExpenseRepository implements PanacheMongoRepository<ExpenseEntity> {
}
