package com.hzy.design.strategy.strategy;

import com.hzy.design.strategy.model.Request;
import com.hzy.design.strategy.model.Response;
import com.hzy.design.strategy.strategy.DealStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomDealStrategy implements DealStrategy {
    public static final String TYPE = "custom_caculate";

    @Override
    public List<Response> deal(List<Request> requests) {
        if(requests.size()==0){
            return new ArrayList<>();
        }else{
            Response response = new Response();
            response.setResult("custom_caculate result");
            return Arrays.asList(response);
        }
    }
}
