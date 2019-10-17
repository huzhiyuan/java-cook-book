/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hzy.flume.annotations;

import java.lang.annotation.Documented;

/**
 * 这个注解用来通知一个包,类或方法的目标受众
 * 当前受众可以是 Public,LimitedPrivate,Private
 * 所有公开类必须带这个注解
 * <ul>
 * <li>Public classes that are not marked with this annotation must be
 * considered by default as {@link Private}.</li>
 * 所有没有标记这个注解的类必须被默认为Private
 *
 * <li>External applications must only use classes that are marked
 * {@link Public}. Avoid using non public classes as these classes
 * could be removed or change in incompatible ways.</li>
 *
 * <li>Flume projects must only use classes that are marked
 * {@link LimitedPrivate} or {@link Public}</li>
 *
 * <li> Methods may have a different annotation that it is more restrictive
 * compared to the audience classification of the class. Example: A class
 * might be {@link Public}, but a method may be {@link LimitedPrivate}
 * </li></ul>
 */
@InterfaceAudience.Public
@InterfaceStability.Evolving
public class InterfaceAudience {
  /**
   * 适用于任何项目和应用
   */
  @Documented public @interface Public {};

  /**
   * 仅适用于注解中指定的类
   * For example, "Common", "HDFS", "MapReduce", "ZooKeeper", "HBase".
   */
  @Documented public @interface LimitedPrivate {
    String[] value();
  };

  /**
   * 仅适用于 Flume 内部
   */
  @Documented public @interface Private {};

  private InterfaceAudience() {} // Audience can't exist on its own  听众不能是自己
}
