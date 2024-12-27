package com.marcoantdev.handler.context;

import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CsvContext {
    private InputStream inputStream;
    private List<String[]> rows;
    private Map<String, Double> expenses;
}
