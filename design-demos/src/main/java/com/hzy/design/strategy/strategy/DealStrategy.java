package com.hzy.design.strategy.strategy;

import com.hzy.design.strategy.model.Request;
import com.hzy.design.strategy.model.Response;

import java.util.List;

public interface DealStrategy {
    List<Response> deal(List<Request> requests);
}
