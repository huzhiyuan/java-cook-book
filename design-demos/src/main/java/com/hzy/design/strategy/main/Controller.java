package com.hzy.design.strategy.main;

import com.alibaba.fastjson.JSONObject;
import com.hzy.design.strategy.model.Request;
import com.hzy.design.strategy.model.Response;
import com.hzy.design.strategy.strategy.DealStrategy;
import com.hzy.design.strategy.strategy.DefaultDealStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {
    StrategyContext strategyContext = new StrategyContext();

    public List<Response> getInfoByTypes(java.util.List<Request> requests){
        Map<String, DealStrategy> typeStrategyMap = strategyContext.getStrategyMap();
        Map<String, List<Request>> typeRequestsMap = new HashMap<>();
        for(String type:typeStrategyMap.keySet()){
            typeRequestsMap.put(type,new ArrayList<Request>());
        }
        for(Request request:requests){
            if(typeRequestsMap.get(request.getType()) != null){
                typeRequestsMap.get(request.getType()).add(request);
            }else{
                typeRequestsMap.get(DefaultDealStrategy.TYPE).add(request);
            }
        }

        List<Response> result = new ArrayList<>();
        for(String type:typeRequestsMap.keySet()){
            result.addAll(typeStrategyMap.get(type).deal(typeRequestsMap.get(type)));
        }
        System.out.println(JSONObject.toJSONString(result));
        return result;
    }

    public static void main(String[] args) {
        Request request = new Request("complex_caculate","ddddddd");
        Request request2 = new Request("xxxxxxx","ddddddd");
        List<Request> requests = new ArrayList<>();
        requests.add(request);
        requests.add(request2);
        Controller controller = new Controller();
        controller.getInfoByTypes(requests);
    }
}



