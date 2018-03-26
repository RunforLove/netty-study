package com.sucre.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyInboundHandler2 extends ChannelInboundHandlerAdapter {

    /**
     * 调用ctx.write(msg)将信息传递到ChannelOutboundHandler
     *
     * ctx.write(msg)方法执行后, 需要调用flush()方法才能令它立即执行。
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception{
        log.info("MyInboundHandler2.channelRead, ctx = {}, msg = {}", ctx, msg);
        ByteBuf result = (ByteBuf) msg;
        byte[] byteArrays = new byte[result.readableBytes()];
        result.readBytes(byteArrays);

        String str = new String(byteArrays);
        System.out.println("Client said: " + str);

        result.release();
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception{
        log.info("MyInboundHandler2.channelReadComplete");
        ctx.flush();
    }
}
