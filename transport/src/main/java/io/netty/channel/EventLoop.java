/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.netty.channel;

import io.netty.util.concurrent.OrderedEventExecutor;

/**
 * Will handle all the I/O operations for a {@link Channel} once registered.
 *
 * One {@link EventLoop} instance will usually handle more than one {@link Channel} but this may depend on
 * implementation details and internals.
 * 为什么使用单例线程池呢？一个线程还使用线程池有什么意义呢？答：需要任务队列，有很多任务需要进行调度，所以需要线程池的特性。但为了多线程的切换导致的性能损耗和为了消除同步，所以使用单个线程。
 */
public interface EventLoop extends OrderedEventExecutor, EventLoopGroup {//一个EventLoop可以处理多个channel？ EventLoop（单例线程池）
    @Override
    EventLoopGroup parent();
}
