package com.marcoantdev.core.usecase;

import com.marcoantdev.core.usecase.ports.ExpenseProcessor;
import com.marcoantdev.core.usecase.ports.UploadExpensesUseCase;
import com.marcoantdev.domain.enums.ExpenseCategoryEnum;
import com.marcoantdev.domain.models.ExpenseEntity;
import com.marcoantdev.domain.repository.ExpenseRepository;
import com.marcoantdev.dtos.ExpenseDto;
import com.marcoantdev.dtos.ExpenseRequestDto;
import com.marcoantdev.factory.ExpenseProcessorFactory;
import com.marcoantdev.mappers.ExpenseMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class UploadExpensesUseCaseImpl implements UploadExpensesUseCase {

    @Inject
    ExpenseRepository expenseRepository;

    @Inject
    ExpenseProcessorFactory processorFactory;

    @Override
    public List<ExpenseDto> uploadExpenses(ExpenseRequestDto expenseRequestDto, String password) throws Exception {
        InputStream fileStream = expenseRequestDto.getFile();
        String format = expenseRequestDto.getFormat();

        ExpenseProcessor processor = processorFactory.getProcessor(format);

        Map<String, Double> groupedExpenses = processor.process(fileStream, password);

        List<ExpenseDto> expenseDtos = new ArrayList<>();
        groupedExpenses.forEach((description, amount) -> {
            ExpenseEntity expense = ExpenseEntity.builder()
                    .description(description)
                    .amount(amount)
                    .category(determineCategory(description))
                    .date(LocalDateTime.now())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            expenseRepository.persist(expense);
            ExpenseDto expenseDto = ExpenseMapper.toDto(expense);
            expenseDtos.add(expenseDto);
        });

        return expenseDtos;
    }

    private String determineCategory(String description) {
        return ExpenseCategoryEnum.fromDescription(description);
    }
}
