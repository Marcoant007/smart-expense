package com.marcoantdev.handler.port;

import com.marcoantdev.handler.PdfContext;

public interface PdfProcessHandlerPort {
  void handle(PdfContext context) throws Exception;

  PdfProcessHandlerPort setNext(PdfProcessHandlerPort nextHandler);
}
