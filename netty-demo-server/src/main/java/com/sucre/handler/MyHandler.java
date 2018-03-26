package com.sucre.handler;

import org.springframework.stereotype.Component;

import io.netty.channel.ChannelInitializer;

/**
 * netty的handler处理类
 *
 * handler可以类比与Servlet中的filter来理解
 */
@Component
public class MyHandler extends ChannelInitializer<io.netty.channel.socket.SocketChannel> {


    /**
     * 1. ChannelInboundHandler之间的传递,通过调用ctx.fireChannelRead(msg)实现;
     * 2. 调用ctx.write(msg)将信息传递到ChannelOutboundHandler;
     * 3. ctx.write()方法执行后，需要调用flush()方法才能令它立即执行;
     * 4. ChannelOutboundHandler 在注册的时候, 需要放在最后一个ChannelInboundHandler之前,否则将无法传递到ChannelOutboundHandler
     * @param ch
     * @throws Exception
     */
    @Override
    protected void initChannel(io.netty.channel.socket.SocketChannel ch) throws Exception {
        /**
         * 注册两个OutboundHandler, 执行顺序为注册顺序的逆序;
         * 所以应该是MyOutboundHandler2    MyOutboundHandler1
         */
        ch.pipeline().addLast(new MyOutBoundHandler1());
        ch.pipeline().addLast(new MyOutboundHandler2());

        /**
         * 注册两个InboundHandler,执行顺序为注册顺序;
         * 所以应该是MyInboundHandler1     MyInboundHandler2
         */
        ch.pipeline().addLast(new MyInboundHandler1());
        ch.pipeline().addLast(new MyInboundHandler2());
    }
}
