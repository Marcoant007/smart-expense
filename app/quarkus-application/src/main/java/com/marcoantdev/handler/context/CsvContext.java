package com.marcoantdev.handler.context;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CsvContext extends BaseContext {
    private List<String[]> rows;
}
