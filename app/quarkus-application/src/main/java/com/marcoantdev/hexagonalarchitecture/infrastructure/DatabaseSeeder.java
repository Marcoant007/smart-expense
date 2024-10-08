package com.marcoantdev.hexagonalarchitecture.infrastructure;

import com.marcoantdev.hexagonalarchitecture.domain.models.ExpenseEntity;
import com.marcoantdev.hexagonalarchitecture.domain.repository.ExpenseRepository;
import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import org.jboss.logging.Logger;

@Startup
@ApplicationScoped
public class DatabaseSeeder {

  private static final Logger LOGGER = Logger.getLogger(DatabaseSeeder.class);

  @Inject
  ExpenseRepository expenseRepository;

  @Transactional
  @PostConstruct
  void init() {
    if (expenseRepository.count() > 0) {
      LOGGER.info("Database already seeded, skipping seeding process.");
      return;
    }

    LOGGER.info("Seeding db with initial data");
    ExpenseEntity expense = ExpenseEntity.builder()
        .description("Compra no mercado")
        .amount(150.0)
        .category("Alimentação")
        .date(LocalDateTime.now())
        .build();

    expenseRepository.persist(expense);
  }
}
