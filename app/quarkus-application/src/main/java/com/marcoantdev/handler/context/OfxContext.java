package com.marcoantdev.handler.context;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OfxContext extends BaseContext {
    private List<String> lines;
}
