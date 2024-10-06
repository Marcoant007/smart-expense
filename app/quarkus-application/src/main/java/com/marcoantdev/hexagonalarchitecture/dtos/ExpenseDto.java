package com.marcoantdev.hexagonalarchitecture.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "ExpenseDTO", description = "Data Transfer Object representing an expense entry")
public class ExpenseDto {

    @NotNull(message = "Description must not be null")
    @Size(min = 1, max = 255, message = "Description must be between 1 and 255 characters")
    @Schema(description = "Description of the expense", example = "Lunch at the restaurant", required = true)
    private String description;

    @NotNull(message = "Amount must not be null")
    @Positive(message = "Amount must be greater than zero")
    @Schema(description = "Amount of the expense", example = "25.75", required = true)
    private double amount;

    @NotNull(message = "Category must not be null")
    @Size(min = 1, max = 50, message = "Category must be between 1 and 50 characters")
    @Schema(description = "Category of the expense", example = "Food", required = true)
    private String category;

    @NotNull(message = "Date must not be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Schema(description = "Date of the expense in format YYYY-MM-DDTHH:MM:SS", example = "2024-09-14T00:00:00", required = true)
    private LocalDateTime date;
}