package com.hzy.flume.channel;

import com.hzy.flume.common.Event;
import com.hzy.flume.common.NamedComponent;
import com.hzy.flume.exception.ChannelException;
import com.hzy.flume.lifecycle.LifecycleAware;

public interface Channel  extends LifecycleAware, NamedComponent {
    /**
     * 将消息放入channel
     * 注意: 这个方法必须在一个事务中完成,失败的话会遇到不可预知的结果
     */
    public void put(Event event) throws ChannelException;

    /**
     * 返回channel中吓一跳可用的event, 如果channel中没有可用events,这个方法必须返回null
     * 同理,这个方法必须在一个事务中完成
     */
    public Event take() throws ChannelException;

    /**
     * @return the transaction instance associated with this channel.
     */
    public Transaction getTransaction();
}
