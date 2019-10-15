package com.hzy.design.test2.buildStrategy;

import com.hzy.design.test2.model.Car;

public interface BuildStrategy {
    Car buildCar(String make);
}
