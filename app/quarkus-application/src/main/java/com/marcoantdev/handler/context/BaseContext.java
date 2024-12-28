package com.marcoantdev.handler.context;

import com.marcoantdev.dtos.ExpenseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class BaseContext {
    private InputStream inputStream;
    private Map<String, Double> expenses = new HashMap<>();
    private String password;
    private List<ExpenseDto> transactions = new ArrayList<>();
}
