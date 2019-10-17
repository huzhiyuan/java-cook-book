package com.hzy.design.strategy.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Request{
    String type;
    String otherInfo;
}
