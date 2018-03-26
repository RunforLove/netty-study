package com.sucre.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyInboundHandler1 extends ChannelInboundHandlerAdapter{

    /**
     * ChannelInboundHandler之间的传递，通过调用ctx.fireChannelRead(msg)实现；
     * 调用ctx.write(msg)将信息传递到ChannelOutboundHandler
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception{
        log.info("MyInboundHandler1.channelRead, ctx = {}, msg = {}", ctx, msg);
        ctx.fireChannelRead(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception{
        log.info("MyInboundHandler1.channelReadComplete");
        ctx.flush();
    }
}
