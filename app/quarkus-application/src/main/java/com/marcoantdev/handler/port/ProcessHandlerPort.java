package com.marcoantdev.handler.port;

public interface ProcessHandlerPort<T> {
    ProcessHandlerPort<T> setNext(ProcessHandlerPort<T> nextHandler);

    void handle(T context) throws Exception;
}
