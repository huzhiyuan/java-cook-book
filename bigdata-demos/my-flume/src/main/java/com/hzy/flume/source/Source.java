package com.hzy.flume.source;

import com.hzy.flume.channel.ChannelProcessor;
import com.hzy.flume.common.NamedComponent;
import com.hzy.flume.lifecycle.LifecycleAware;

public interface Source extends LifecycleAware, NamedComponent {
    public void setChannelProcessor(ChannelProcessor channelProcessor);

    public ChannelProcessor getChannelProcessor();
}
