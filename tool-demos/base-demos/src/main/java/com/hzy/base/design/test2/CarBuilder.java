package com.hzy.design.test2;

import com.hzy.design.test2.model.Car;

public class CarBuilder {
    public static void main(String[] args) {
        CarBuilder carBuilder = new CarBuilder();
        carBuilder.buildCar("mazda6-sport");
        carBuilder.buildCar("ferrari-california");
    }

    private Car buildCar(String make) {
        Car car = new Car(make);
        if ("mazda6-sport".equals(make)) {
            car.setBodyConfiguration("sedan");
            car.setEnginePlacement("front");
            car.setEngineType("SkyActiv-G");
            car.setManufacturer("Mazda");
        } else if ("ferrari-california".equals(make)) {
            car.setBodyConfiguration("convertible");
            car.setEnginePlacement("front-engine, rear wheel drive");
            car.setEngineType("V8");
            car.setManufacturer("Ferrari");
        }
        return car;
    }
}
