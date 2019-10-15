package com.hzy.design.test2;

import com.hzy.design.test2.buildStrategy.BuildStrategy;
import com.hzy.design.test2.buildStrategy.FerrariCarBuilder;
import com.hzy.design.test2.buildStrategy.MazdaCarBuilder;
import com.hzy.design.test2.model.Car;
import com.hzy.design.test2.safetyStrategy.CarSafety1;
import com.hzy.design.test2.safetyStrategy.SafetyStrategy;

public class CarBuilder2 {
    BuildStrategy buildStrategy = new MazdaCarBuilder();

    public static void main(String[] args) {
        CarBuilder2 carBuilder = new CarBuilder2();

        carBuilder.buildStrategy = new MazdaCarBuilder();
        Car mazda = carBuilder.buildCar("maz");
    }

    public void setStategy(BuildStrategy stategy)
    {
        this.buildStrategy = stategy;
    }

    public Car buildCar(String make)
    {
        return buildStrategy.buildCar(make);
    }
}
