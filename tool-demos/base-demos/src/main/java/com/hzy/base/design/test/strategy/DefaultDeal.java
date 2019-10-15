package com.hzy.design.test.strategy;

import com.hzy.design.test.model.Request;
import com.hzy.design.test.model.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultDeal implements DealStrategy{
    public static final String TYPE = "default";

    @Override
    public List<Response> deal(List<Request> requests) {
        if(requests.size()==0){
            return new ArrayList<>();
        }else{
            Response response = new Response();
            response.setResult("default result");
            return Arrays.asList(response);
        }
    }
}
