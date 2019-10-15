package com.hzy.design.test2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Car
{

    private String manufacturer;
    private String engineType;
    private String enginePlacement;
    private String bodyConfiguration;
    private String make;

    public Car(String make){
        this.make = make;
    }
}
