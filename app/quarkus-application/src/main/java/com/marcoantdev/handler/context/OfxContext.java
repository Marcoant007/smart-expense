package com.marcoantdev.handler.context;

import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
import java.util.Map;

@Getter
@Setter
public class OfxContext {
    private InputStream inputStream;
    private String extractedText;
    private Map<String, Double> expenses;
}
