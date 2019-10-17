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
 * 这个接口用来告知使用者指定包,类,方法的稳定性(版本?)
 * 所有带Public,LimitedPrivate注解的类必须带这个注解
 * 带Private注解的类被认为是不稳定的,除非一个新的稳定性注解出现
 * //hzy TODO: 也就是说,这个注解是用来表示flume开发的东西是否稳定,外界能用还是只供flume自己用
 * 带Stable注解的类一定不要发布一些不兼容的改变
 */
@InterfaceAudience.Public
@InterfaceStability.Evolving
public class InterfaceStability {  //接口稳定性
  /**
   * Can evolve while retaining compatibility for minor release boundaries.;
   * can break compatibility only at major release (ie. at m.0).
   */
  @Documented
  public @interface Stable {};

  /**
   * Evolving, but can break compatibility at minor release (i.e. m.x)
   */
  @Documented
  public @interface Evolving {};

  /**
   * No guarantee is provided as to reliability or stability across any
   * level of release granularity.
   */
  @Documented
  public @interface Unstable {};
}