package com.marcoantdev.dtos;

import jakarta.ws.rs.FormParam;
import java.io.InputStream;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class ExpensePdfDto {
  @FormParam("file")
  @PartType("application/octet-stream")
  private InputStream file;

  public InputStream getFile() {
    return file;
  }

  public void setFile(InputStream file) {
    this.file = file;
  }
}
