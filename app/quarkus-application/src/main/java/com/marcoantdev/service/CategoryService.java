package com.marcoantdev.service;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Map;

@ApplicationScoped
public class CategoryService {
    private static final Map<String, String> CATEGORY_MAP = Map.of(
            "MERCADOLIVRE", "Compras",
            "HORTIFRUTI", "Alimentação",
            "IFOOD", "Delivery",
            "UBER", "Transporte",
            "SUPERMARKET", "Alimentação",
            "AMAZON", "Compras",
            "PIX", "Transferência",
            "GOOGLE", "Assinaturas",
            "ALMYR", "Alimentação",
            "IFD", "Alimentação"
    );

    public String identifyCategory(String description) {
        return CATEGORY_MAP.entrySet().stream()
                .filter(entry -> description.toUpperCase().contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse("Outros");
    }
}
