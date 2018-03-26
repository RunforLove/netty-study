package com.sucre.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyClientHandler extends ChannelInboundHandlerAdapter{

    /**
     * 读取服务端的信息
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception{
        log.info("MyClientHandler.channelRead");
        ByteBuf result = (ByteBuf)msg;
        byte[] byteArrays = new byte[result.readableBytes()];
        result.readBytes(byteArrays);

        result.release();
        ctx.close();
        System.out.println("Server said: " + new String(byteArrays));
    }

    /**
     * 当连接建立的时候,向服务端发送消息, channelActive事件当连接建立的时候会触发
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception{
        log.info("MyClientHandler.channelActive");
        String msg = "Are you ok?";
        ByteBuf encoded = ctx.alloc().buffer(4 * msg.length());
        encoded.writeBytes(msg.getBytes());

        ctx.write(encoded);
        ctx.flush();
    }
}
