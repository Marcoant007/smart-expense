package com.marcoantdev.core.usecase.ports;

import java.io.InputStream;
import java.util.Map;

public interface ExpenseProcessor {
    Map<String, Double> process(InputStream inputStream, String password);
}
