package com.marcoantdev.hexagonalarchitecture.handler;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.InputStream;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class PdfContext {
    private InputStream inputStream;
    private String password;
    private PDDocument document;
    private String extractedText;
    private Map<String, Double> expenses;
}
