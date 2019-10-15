package com.hzy.design.test2.buildStrategy;

import com.hzy.design.test2.model.Car;

public class FerrariCarBuilder implements BuildStrategy {

    public Car buildCar(String make){
        Car car = new Car(make);
        car.setBodyConfiguration("sedan");
        car.setEnginePlacement("front");
        car.setEngineType("SkyActiv-G");
        car.setManufacturer("Ferrari");
        return car;
    }
}
