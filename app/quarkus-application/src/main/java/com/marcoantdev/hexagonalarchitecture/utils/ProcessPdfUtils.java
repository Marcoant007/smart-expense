package com.marcoantdev.hexagonalarchitecture.utils;

import jakarta.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


@ApplicationScoped
public class ProcessPdfUtils {

  public Map<String, Double> processPdf(InputStream inputStream) {
    Map<String, Double> expensesMap = new HashMap<>();

    try (PDDocument document = PDDocument.load(inputStream)) {
      PDFTextStripper pdfStripper = new PDFTextStripper();
      String text = pdfStripper.getText(document);
      Pattern pattern = Pattern.compile("([A-Za-zÀ-ÿ\\s]+?)\\s*(Parcela.*?\\d+\\sde\\s\\d+)?\\s*R\\$\\s([0-9]+,\\d{2})");
      Matcher matcher = pattern.matcher(text);

      while (matcher.find()) {
        String expenseName = matcher.group(1).trim();
        String valueString = matcher.group(3).replace(",", ".");
        Double value = Double.parseDouble(valueString);

        expensesMap.merge(expenseName, value, Double::sum);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return expensesMap;
  }
}
