package com.sucre.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyClientHandler extends ChannelInboundHandlerAdapter{

    /**
     * 读取服务端的信息;
     * 【有数据可以读取的时候触发】
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
        log.info("MyClientHandler.channelActive 与服务端连接建立");
        String msg = "Are you ok?";

        // 申请一块缓存(buffer)写入数据
        ByteBuf encoded = ctx.alloc().buffer(4 * msg.length());
        encoded.writeBytes(msg.getBytes());

        // 将buf保存到ChannelOutboundBuffer中
        // 将ChannelOutboundBuffer中的buf输出到socketChannel中
        ctx.write(encoded);
        ctx.flush();
    }
}
