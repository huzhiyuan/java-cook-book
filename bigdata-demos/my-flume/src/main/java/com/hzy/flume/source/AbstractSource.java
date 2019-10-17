package com.hzy.flume.source;

import com.google.common.base.Preconditions;
import com.hzy.flume.channel.ChannelProcessor;
import com.hzy.flume.lifecycle.LifecycleState;

/**
 * TODO 为什么方法都要加上synchronized?
 */
public class AbstractSource implements Source {
    private ChannelProcessor channelProcessor;
    private String name;
    private LifecycleState lifecycleState;

    public AbstractSource() {
        lifecycleState = LifecycleState.IDLE;
    }

    public synchronized void start() {
        Preconditions.checkState(channelProcessor != null, "No channel processor configured");
        lifecycleState = LifecycleState.START;
    }

    public synchronized void stop() {
        lifecycleState = LifecycleState.STOP;
    }

    public synchronized LifecycleState getLifecycleState() {
        return lifecycleState;
    }

    public synchronized void setChannelProcessor(ChannelProcessor channelProcessor) {
        this.channelProcessor = channelProcessor;
    }

    public synchronized ChannelProcessor getChannelProcessor() {
        return channelProcessor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return this.getClass().getName() + "{name:" + name + ",state:" + lifecycleState + "}";
    }

}
