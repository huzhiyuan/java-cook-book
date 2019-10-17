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

import com.hzy.flume.exception.EventDeliveryException;

/**
 * source需要额外的操作者将其放入channel
 * @see org.apache.flume.source.EventDrivenSourceRunner
 */
public interface PollableSource extends Source {  //轮训的source
  /**
   * <p>
   * 试图从source中拉取一个项目,将其放入channel
   * </p>
   * When driven by an {@link EventDrivenSourceRunner} process is guaranteed
   * to be called only by a single thread at a time, with no concurrency.
   * Any other mechanism driving a pollable source must follow the same
   * semantics.
   * process被保证同一时间只有一个线程访问,没有并发
   * </p>
   * @return {@code READY} if one or more events were created from the source.
   * {@code BACKOFF} if no events could be created from the source.
   * @throws EventDeliveryException 如果投递到关联的channel中失败了,或者从source中获取数据失败
   */
  public Status process() throws EventDeliveryException;

  public long getBackOffSleepIncrement();

  public long getMaxBackOffSleepInterval();

  public static enum Status {
    READY, BACKOFF
  }

}
