package com.marcoantdev.domain.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Getter;

public enum ExpenseCategoryEnum {
  ALIMENTACAO("Alimentação", "mercado"),
  TRANSPORTE("Transporte", "transporte"),
  RESTAURANTES("Restaurantes", "restaurante"),
  LAZER("Lazer", "lazer"),
  OUTROS("Outros", "");

  private static final Map<String, ExpenseCategoryEnum> CATEGORY_MAP =
      Arrays.stream(values())
          .collect(Collectors.toMap(c -> c.keyword, Function.identity()));
  @Getter
  private final String name;
  private final String keyword;

  ExpenseCategoryEnum(String name, String keyword) {
    this.name = name;
    this.keyword = keyword;
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
