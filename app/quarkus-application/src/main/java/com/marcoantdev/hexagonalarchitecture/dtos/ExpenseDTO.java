package com.marcoantdev.hexagonalarchitecture.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@Builder
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
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  @Schema(description = "Date of the expense in format YYYY-MM-DDTHH:MM:SS", example = "2024-09-14T00:00:00", required = true)
  private LocalDateTime date;
}
