package com.marcoantdev.domain.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum ExpenseCategoryEnum {
    ALIMENTACAO("Alimentação", Arrays.asList("mercado", "lanchonete", "restaurante", "ifd", "honest")),
    TRANSPORTE("Transporte", Arrays.asList("transporte", "uber", "99")),
    RESTAURANTES("Restaurantes", Arrays.asList("pizzaria", "churrascaria", "food", "zamp")),
    LAZER("Lazer", Arrays.asList("cinema", "show", "teatro")),
    OUTROS("Outros", Collections.emptyList());

    private static final Map<String, ExpenseCategoryEnum> CATEGORY_MAP =
            Arrays.stream(values())
                    .flatMap(c -> c.keywords.stream().map(keyword -> Map.entry(keyword, c)))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    @Getter
    private final String name;
    private final List<String> keywords;

    ExpenseCategoryEnum(String name, List<String> keywords) {
        this.name = name;
        this.keywords = keywords;
    }

    public static String fromDescription(String description) {
        return CATEGORY_MAP.entrySet().stream()
                .filter(entry -> description.toLowerCase().contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(OUTROS)
                .getName();
    }
}

