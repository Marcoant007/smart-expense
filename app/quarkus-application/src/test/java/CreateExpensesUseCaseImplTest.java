import com.marcoantdev.hexagonalarchitecture.core.usecase.CreateExpenseUseCaseImpl;
import com.marcoantdev.hexagonalarchitecture.domain.models.ExpenseEntity;
import com.marcoantdev.hexagonalarchitecture.domain.repository.ExpenseRepository;
import com.marcoantdev.hexagonalarchitecture.dtos.ExpenseDTO;
import com.marcoantdev.hexagonalarchitecture.dtos.ExpenseResponseDTO;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@QuarkusTest
public class CreateExpensesUseCaseImplTest {
    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private CreateExpenseUseCaseImpl createExpenseUseCase;

    @BeforeEach
    void setUp() {
      MockitoAnnotations.openMocks(this);
    }

    @Test
    void createExpense_shouldReturnCreatedExpense() {
      // Arrange
      ExpenseDTO expenseDTO = new ExpenseDTO("Mercado", 100.0, "Alimentação", "2024-09-01");
      ExpenseEntity expenseEntity = expenseDTO.toEntity();

      doNothing().when(expenseRepository).persist(any(ExpenseEntity.class));

      // Act
      ExpenseResponseDTO responseDTO = createExpenseUseCase.createExpense(expenseDTO);

      // Assert
      assertThat(responseDTO).isNotNull();
      assertThat(responseDTO.getDescription()).isEqualTo("Mercado");
      verify(expenseRepository, times(1)).persist(any(ExpenseEntity.class));
    }
}
