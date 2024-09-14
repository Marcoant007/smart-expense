package com.marcoantdev.hexagonalarchitecture.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "ExpenseDTO", description = "Data Transfer Object representing an expense entry")
public class ExpenseDTO {

  @NotNull
  @Size(min = 1, max = 255)
  @Schema(description = "Description of the expense", example = "Lunch at the restaurant", required = true)
  private String description;

  @NotNull
  @Min(0)
  @Schema(description = "Amount of the expense", example = "25.75", required = true)
  private double amount;

  @NotNull
  @Size(min = 1, max = 50)
  @Schema(description = "Category of the expense", example = "Food", required = true)
  private String category;

  @NotNull
  @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date should be in the format YYYY-MM-DD")
  @Schema(description = "Date of the expense in format YYYY-MM-DD", example = "2024-09-14", required = true)
  private String date;
}
