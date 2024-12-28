package com.marcoantdev.handler.context;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.pdfbox.pdmodel.PDDocument;

@Getter
@Setter
@NoArgsConstructor
public class PdfContext extends BaseContext {
    private String extractedText;
    private PDDocument document;
}
