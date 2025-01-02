package com.marcoantdev.service;

import com.marcoantdev.core.usecase.ports.ExpenseProcessor;
import com.marcoantdev.handler.categorize.ofx.CategorizeOfxExpensesHandler;
import com.marcoantdev.handler.context.OfxContext;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class OfxProcessorService implements ExpenseProcessor {

  @Override
  public Map<String, Double> process(InputStream inputStream, String password) {
    try {
      if (inputStream == null || inputStream.available() == 0) {
        throw new IllegalArgumentException("OFX InputStream cannot be null or empty.");
      }

      List<String> ofxLines = new BufferedReader(new InputStreamReader(inputStream))
          .lines()
          .toList();

      OfxContext context = new OfxContext();
      context.setInputStream(inputStream);
      context.setLines(ofxLines);
      context.setPassword(password);

      CategorizeOfxExpensesHandler categorizeHandler = new CategorizeOfxExpensesHandler();
      categorizeHandler.handle(context);

      return context.getExpenses();
    } catch (Exception e) {
      throw new RuntimeException("Error processing OFX file: " + e.getMessage(), e);
    }
  }
}
