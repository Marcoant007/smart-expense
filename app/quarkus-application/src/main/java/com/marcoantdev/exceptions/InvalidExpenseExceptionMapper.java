package com.marcoantdev.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class InvalidExpenseExceptionMapper implements ExceptionMapper<InvalidExpenseException> {

  @Override
  public Response toResponse(InvalidExpenseException exception) {
    ErrorCode errorCode = ErrorCode.UNPROCESSABLE_ENTITY;
    return Response.status(errorCode.getStatusCode())
        .entity(new ErrorResponse(errorCode.getMessage()))
        .build();
  }
}
