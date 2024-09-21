package com.marcoantdev.hexagonalarchitecture.adapter.rest;

import com.marcoantdev.hexagonalarchitecture.core.usecase.ports.CreateExpenseUseCase;
import com.marcoantdev.hexagonalarchitecture.core.usecase.ports.ListExpenseUseCase;
import com.marcoantdev.hexagonalarchitecture.core.usecase.ports.UploadExpensesUseCase;
import com.marcoantdev.hexagonalarchitecture.dtos.ExpenseDTO;
import com.marcoantdev.hexagonalarchitecture.dtos.ExpenseRequestDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import java.util.List;
import java.util.Map;


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
        List<ExpenseDTO> expenses = listExpenseUseCase.listAllExpenses();
        return Response.ok(expenses).build();
    }

    @POST
    public Response createExpense(ExpenseDTO expenseDTO) {
        ExpenseDTO createdExpense = createExpenseUseCase.createExpense(expenseDTO);
        return Response.status(Response.Status.CREATED).entity(createdExpense).build();
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadPdf(@MultipartForm ExpenseRequestDto expenseRequestDto, @QueryParam("password") String password) {
        try {
            Map<String, Double> groupedExpenses = uploadExpensesUseCase.uploadExpenses(expenseRequestDto, password);
            return Response.ok(groupedExpenses).build();
        } catch (Exception e) {
            LOGGER.error("Error to import PDF: ", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error to import PDF").build();
        }
    }
}
