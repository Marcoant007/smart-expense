package com.marcoantdev.hexagonalarchitecture.core.usecase;

import com.marcoantdev.hexagonalarchitecture.core.usecase.ports.ListExpenseUseCase;
import com.marcoantdev.hexagonalarchitecture.domain.repository.ExpenseRepository;
import com.marcoantdev.hexagonalarchitecture.dtos.ExpenseDTO;
import com.marcoantdev.hexagonalarchitecture.mappers.ExpenseMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ListExpenseUseCaseImpl implements ListExpenseUseCase {

    @Inject
    ExpenseRepository expenseRepository;

    @Override
    public List<ExpenseDTO> listAllExpenses() {
        return expenseRepository.listAll().stream()
                .map(ExpenseMapper::toDto)
                .collect(Collectors.toList());
    }
}
