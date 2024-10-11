package com.marcoantdev.exceptions;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

  @Override
  public Response toResponse(ConstraintViolationException exception) {
    StringBuilder message = new StringBuilder("Validation failed: ");

    exception.getConstraintViolations().forEach(violation -> {
      message.append(violation.getMessage()).append("; ");
    });

    ErrorResponse errorResponse = new ErrorResponse(message.toString().trim());

    return Response.status(Response.Status.BAD_REQUEST)
        .entity(errorResponse)
        .build();
  }
}
