package com.marcoantdev.hexagonalarchitecture.adapter.rest;

import com.marcoantdev.hexagonalarchitecture.core.usecase.ports.CreateExpenseUseCase;
import com.marcoantdev.hexagonalarchitecture.core.usecase.ports.ListExpenseUseCase;
import com.marcoantdev.hexagonalarchitecture.core.usecase.ports.UploadExpensesUseCase;
import com.marcoantdev.hexagonalarchitecture.dtos.ExpenseDTO;
import com.marcoantdev.hexagonalarchitecture.dtos.ExpensePdfDto;
import com.marcoantdev.hexagonalarchitecture.dtos.ExpenseResponseDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;


@Path("/expenses")
public class ExpenseController {

  private static final Logger LOGGER = Logger.getLogger(ExpenseController.class);


  @Inject
  CreateExpenseUseCase createExpenseUseCase;

  @Inject
  ListExpenseUseCase listExpenseUseCase;

  @Inject
  UploadExpensesUseCase uploadExpensesUseCase;

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

  @POST
  @Path("/upload")
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  public Response uploadPdf(@MultipartForm ExpensePdfDto form) {
    try {
      Map<String, Double> groupedExpenses = uploadExpensesUseCase.uploadExpenses(form.getFile());
      return Response.ok(groupedExpenses).build();
    } catch (Exception e) {
      LOGGER.error("Erro ao importar PDF: ", e);
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao importar PDF").build();
    }
  }
}
