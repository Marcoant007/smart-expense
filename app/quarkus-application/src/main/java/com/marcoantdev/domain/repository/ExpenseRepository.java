package com.marcoantdev.domain.repository;

import com.marcoantdev.domain.models.ExpenseEntity;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import org.bson.types.ObjectId;

@ApplicationScoped
public class ExpenseRepository implements PanacheMongoRepository<ExpenseEntity> {

  public List<ExpenseEntity> findByCategory(String category) {
    return list("category", category);
  }

  public List<ExpenseEntity> findByAmountGreaterThan(double amount) {
    return list("amount > ?1", amount);
  }

  public ExpenseEntity findById(String id) {
    return find("_id", new ObjectId(id)).firstResult();
  }
}
