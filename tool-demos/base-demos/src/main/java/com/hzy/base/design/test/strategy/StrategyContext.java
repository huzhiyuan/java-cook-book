package com.hzy.design.test.strategy;

import com.hzy.design.test.model.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StrategyContext {
    Map<String,DealStrategy> strategyMap = new HashMap<>();
    public StrategyContext() {
        strategyMap.put(ComplexDeal.TYPE,new ComplexDeal());
        strategyMap.put(DefaultDeal.TYPE,new DefaultDeal());
    }
    public Map<String,DealStrategy> getStrategyMap(){
        return this.strategyMap;
    }
}
