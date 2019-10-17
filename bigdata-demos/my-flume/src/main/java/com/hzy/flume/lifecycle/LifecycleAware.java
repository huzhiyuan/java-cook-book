package com.hzy.flume.lifecycle;

/**
 * 生命周期接口
 */
public interface LifecycleAware {
    public void start();
    public void stop();

    public LifecycleState getLifecycleState();
}
