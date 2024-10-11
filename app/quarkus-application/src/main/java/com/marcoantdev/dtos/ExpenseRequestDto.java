package com.marcoantdev.dtos;

import jakarta.ws.rs.FormParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import java.io.InputStream;

@Getter
@Setter
@NoArgsConstructor
public class ExpenseRequestDto {

  @FormParam("file")
  @PartType("application/octet-stream")
  private InputStream file;
}
