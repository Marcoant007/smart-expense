package com.marcoantdev.exceptions;

import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
  NOT_AUTHORIZED("Authorization failed due to insufficient permissions", Response.Status.FORBIDDEN.getStatusCode()),
  INVALID_CLIENT("Client authentication failed", Response.Status.UNAUTHORIZED.getStatusCode()),
  RESOURCE_NOT_FOUND("The specified resource was not found", Response.Status.NOT_FOUND.getStatusCode()),
  CONFLICT("The request could not be completed due to a conflict", Response.Status.CONFLICT.getStatusCode()),
  UNPROCESSABLE_ENTITY("The request is semantically incorrect or fails business validation", 422),
  INVALID_REQUEST_SYNTAX("The request is malformed, syntactically incorrect, or violates the schema",
      Response.Status.BAD_REQUEST.getStatusCode());

  private final String message;
  private final int statusCode;
}
