package com.marcoantdev.utils;

public class CsvUtils {
    public static String cleanMonetaryValue(String rawValue) {
        if (rawValue == null || rawValue.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty.");
        }

        String cleanedValue = rawValue
                .replaceAll("[^0-9,.-]", "")
                .replace(",", ".")
                .trim();

        if (cleanedValue.isEmpty()) {
            throw new IllegalArgumentException("Cleaned value is empty after normalization.");
        }

        return cleanedValue;
    }


    public static double parseDouble(String cleanedValue) {
        return Double.parseDouble(cleanedValue);
    }
}
