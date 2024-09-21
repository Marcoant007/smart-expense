package com.marcoantdev.hexagonalarchitecture.handler;

import com.marcoantdev.hexagonalarchitecture.handler.port.PdfProcessHandlerPort;

public abstract class BasePdfHandler implements PdfProcessHandlerPort {
    protected PdfProcessHandlerPort nextHandler;

    @Override
    public PdfProcessHandlerPort setNext(PdfProcessHandlerPort nextHandler) {
        this.nextHandler = nextHandler;
        return this.nextHandler;
    }

    protected void next(PdfContext context) throws Exception {
        if (nextHandler != null) {
            nextHandler.handle(context);
        }
    }
}
