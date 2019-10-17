package com.hzy.design.strategy.main;

import com.hzy.design.strategy.strategy.ComplexDealStrategy;
import com.hzy.design.strategy.strategy.CustomDealStrategy;
import com.hzy.design.strategy.strategy.DealStrategy;
import com.hzy.design.strategy.strategy.DefaultDealStrategy;

import java.util.HashMap;
import java.util.Map;

public class StrategyContext {
    Map<String, DealStrategy> strategyMap = new HashMap<>();
    public StrategyContext() {
        strategyMap.put(ComplexDealStrategy.TYPE,new ComplexDealStrategy());
        strategyMap.put(DefaultDealStrategy.TYPE,new DefaultDealStrategy());
        strategyMap.put(CustomDealStrategy.TYPE,new CustomDealStrategy());
    }
    public Map<String,DealStrategy> getStrategyMap(){
        return this.strategyMap;
    }
}
