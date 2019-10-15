package com.hzy.design.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Request{
    String type;
    String otherInfo;
}
