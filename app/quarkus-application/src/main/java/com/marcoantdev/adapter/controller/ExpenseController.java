package com.marcoantdev.adapter.controller;

import com.marcoantdev.core.usecase.ports.CreateExpenseUseCase;
import com.marcoantdev.core.usecase.ports.ListExpenseUseCase;
import com.marcoantdev.core.usecase.ports.UploadExpensesUseCase;
import com.marcoantdev.dtos.ExpenseDto;
import com.marcoantdev.dtos.ExpenseRequestDto;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import java.util.List;


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
        List<ExpenseDto> expenses = listExpenseUseCase.listAllExpenses();
        return Response.ok(expenses).build();
    }

    @POST
    public Response createExpense(@Valid @NotNull ExpenseDto expenseDTO) {
        ExpenseDto createdExpense = createExpenseUseCase.createExpense(expenseDTO);
        return Response.status(Response.Status.CREATED).entity(createdExpense).build();
    }

//    @POST
//    @Path("/upload")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    public Response uploadPdf(@MultipartForm ExpenseRequestDto expenseRequestDto, @QueryParam("password") String password) {
//        try {
//            List<ExpenseDto> groupedExpenses = uploadExpensesUseCase.uploadExpenses(expenseRequestDto, password);
//            return Response.ok(groupedExpenses).build();
//        } catch (Exception e) {
//            LOGGER.error("Error importing file: ", e);
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error importing file").build();
//        }
//    }


    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(@MultipartForm ExpenseRequestDto expenseRequestDto,
                               @QueryParam("format") String format,
                               @QueryParam("password") String password) {
        try {
            expenseRequestDto.setFormat(format);
            List<ExpenseDto> groupedExpenses = uploadExpensesUseCase.uploadExpenses(expenseRequestDto, password);
            return Response.ok(groupedExpenses).build();
        } catch (Exception e) {
            LOGGER.error("Error importing file: ", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error importing file").build();
        }
    }
}
