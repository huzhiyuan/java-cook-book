package com.hzy.flume.channel;

import com.hzy.flume.common.Event;
import com.hzy.flume.common.NamedComponent;
import com.hzy.flume.config.Configurable;

import java.util.List;

/**
 * 消息管道选择器
 */
public interface ChannelSelector extends NamedComponent, Configurable {

    /**
     * 设置所有可供选择的channels
     * @param channels
     */
    public void setChannels(List<Channel> channels);

    /**
     * 返回channel选择器给event选择的channels, 如果写入失败,一定要把消息转达回source
     * @param event
     * @return
     */
    public List<Channel> getRequiredChannels(Event event);

    /**
     * 返回channel选择器给event选择的可选channels
     * 如果写失败, 那么写失败的消息一定要被忽略
     */
    public List<Channel> getOptionalChannels(Event event);

    /**
     * 返回所有channel
     */
    public List<Channel> getAllChannels();

}
