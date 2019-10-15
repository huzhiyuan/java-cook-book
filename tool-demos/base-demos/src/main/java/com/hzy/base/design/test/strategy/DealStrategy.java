package com.hzy.design.test.strategy;

import com.hzy.design.test.model.Request;
import com.hzy.design.test.model.Response;

import java.util.List;

public interface DealStrategy {
    List<Response> deal(List<Request> requests);
}
