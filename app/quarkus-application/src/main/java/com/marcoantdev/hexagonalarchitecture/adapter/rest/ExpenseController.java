package com.marcoantdev.hexagonalarchitecture.adapter.rest;

import com.marcoantdev.hexagonalarchitecture.core.usecase.ports.CreateExpenseUseCase;
import com.marcoantdev.hexagonalarchitecture.core.usecase.ports.ListExpenseUseCase;
import com.marcoantdev.hexagonalarchitecture.dtos.ExpenseDTO;
import com.marcoantdev.hexagonalarchitecture.dtos.ExpenseResponseDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/expenses")
public class ExpenseController {

  @Inject
  CreateExpenseUseCase createExpenseUseCase;

  @Inject
  ListExpenseUseCase listExpenseUseCase;

  @GET
  public Response listAllExpenses() {
    List<ExpenseResponseDTO> expenses = listExpenseUseCase.listAllExpenses();
    return Response.ok(expenses).build();
  }

  @POST
  public Response createExpense(ExpenseDTO expenseDTO) {
    ExpenseResponseDTO createdExpense = createExpenseUseCase.createExpense(expenseDTO);
    return Response.status(Response.Status.CREATED).entity(createdExpense).build();
  }

}
