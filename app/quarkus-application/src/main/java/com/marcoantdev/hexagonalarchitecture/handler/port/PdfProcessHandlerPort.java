package com.marcoantdev.hexagonalarchitecture.handler.port;

import com.marcoantdev.hexagonalarchitecture.handler.PdfContext;

public interface PdfProcessHandlerPort {
    void handle(PdfContext context) throws Exception;

    PdfProcessHandlerPort setNext(PdfProcessHandlerPort nextHandler);
}
