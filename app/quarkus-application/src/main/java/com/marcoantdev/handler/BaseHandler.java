package com.marcoantdev.handler;

import com.marcoantdev.handler.port.ProcessHandlerPort;

public abstract class BaseHandler<T> implements ProcessHandlerPort<T> {
    protected ProcessHandlerPort<T> nextHandler;

    @Override
    public ProcessHandlerPort<T> setNext(ProcessHandlerPort<T> nextHandler) {
        this.nextHandler = nextHandler;
        return this.nextHandler;
    }

    protected void next(T context) throws Exception {
        if (nextHandler != null) {
            nextHandler.handle(context);
        }
    }
}
