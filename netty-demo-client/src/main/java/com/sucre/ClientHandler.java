package com.sucre;

import com.sucre.handler.MyClientHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientHandler {

    public void connect(String host, int port) throws Exception{
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new MyClientHandler());

            ChannelFuture future = bootstrap.connect(host,port).sync();


            future.channel().closeFuture().sync();
        }catch (Exception ex){
            log.error("ex = {}", ex);
        }finally {
            workerGroup.shutdownGracefully();
        }
    }
}
