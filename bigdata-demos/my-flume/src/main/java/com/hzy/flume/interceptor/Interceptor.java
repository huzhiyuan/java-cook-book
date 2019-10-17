package com.hzy.flume.interceptor;

import com.hzy.flume.common.Event;
import com.hzy.flume.config.Configurable;

import java.util.List;

public interface Interceptor {
    public void initialize();

    /**
     * 拦截一个消息,
     * 返回原始消息,更新后的消息
     * 或者null(表示消息将被丢弃)
     * @param event
     * @return
     */
    public Event intercept(Event event);

    /**
     * 拦截一批消息
     * 注意:
     * 1.返回的消息个数不能比输入的多
     * 2.不能返回null. 可以返回empty list
     * @param events
     * @return
     */
    public List<Event> intercept(List<Event> events);

    /**
     * 执行任何拦截器需要的关闭操作
     */
    public void close();

    /**
     * Builder实现着必须有一个无参构造器
     */
    public interface Builder extends Configurable{
        public Interceptor build();
    }
}
