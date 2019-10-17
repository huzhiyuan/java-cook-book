/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.hzy.flume.config.runner;

import com.hzy.flume.annotations.InterfaceAudience;
import com.hzy.flume.annotations.InterfaceStability;
import com.hzy.flume.config.NamedComponent;
import com.hzy.flume.lifecycle.LifecycleAware;
/**
 * 一个source 生成事件(Event), 然后调用ChannelProcessor的方法将event放到channel中
 * <p>
 * Sources are associated with unique {@linkplain NamedComponent names} that can
 * be used for separating configuration and working namespaces.
 * source可以和唯一的名称绑定, 然后被用在separating configuration(分离配置?) and working namespaces(工作名称空间?)
 *
 * 没有线程安全方面的保证?
 * No guarantees are given regarding thread safe access.
 *
 */
@InterfaceAudience.Public
@InterfaceStability.Stable
public interface Source extends LifecycleAware, NamedComponent {

  /**
   * Specifies which channel processor will handle this source's events.
   *
   * @param channelProcessor
   */
  public void setChannelProcessor(ChannelProcessor channelProcessor);

  /**
   * Returns the channel processor that will handle this source's events.
   */
  public ChannelProcessor getChannelProcessor();

}
