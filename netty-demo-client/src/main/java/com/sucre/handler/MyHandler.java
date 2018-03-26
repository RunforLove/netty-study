package com.sucre.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class MyHandler extends ChannelInitializer<SocketChannel>{


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new MyClientHandler());
    }
}
