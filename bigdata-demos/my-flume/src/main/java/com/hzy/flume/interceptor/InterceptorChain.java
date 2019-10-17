package com.hzy.flume.interceptor;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.hzy.flume.common.Event;

import java.util.Iterator;
import java.util.List;

public class InterceptorChain implements Interceptor{
    private List<Interceptor> interceptors;

    public InterceptorChain(){
        interceptors = Lists.newLinkedList();
    }

    public Event intercept(Event event) {
        for(Interceptor interceptor:interceptors){
            if(event == null){
                return null;
            }
            event = interceptor.intercept(event);
        }
        return event;
    }

    public List<Event> intercept(List<Event> events) {
        for (Interceptor interceptor : interceptors) {
            if (events.isEmpty()) {
                return events;
            }
            events = interceptor.intercept(events);
            Preconditions.checkNotNull(events,
                    "Event list returned null from interceptor %s", interceptor);
        }
        return events;
    }

    public void setInterceptors(List<Interceptor> interceptors) {
        this.interceptors = interceptors;
    }

    public void initialize() {
        Iterator<Interceptor> iter = interceptors.iterator();
        while(iter.hasNext()){
            Interceptor interceptor = iter.next();
            interceptor.initialize();
        }
    }

    public void close() {
        Iterator<Interceptor> iter = interceptors.iterator();
        while(iter.hasNext()){
            Interceptor interceptor = iter.next();
            interceptor.close();
        }
    }
}
