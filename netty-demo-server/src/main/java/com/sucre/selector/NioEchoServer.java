package com.sucre.selector;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioEchoServer {

    private static final int TIMEOUT = 3000;

    public static void main(String args[]) throws Exception{

        // 打开服务端Socket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 打开Selector
        Selector selector = Selector.open();

        // 服务端Socket监听8080端口,并配置为非阻塞模式
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(false);

        // 将channle注册到select中
        // 通常我们都是先注册一个OP_ACCEPT事件，然后在OP_ACCEPT到来时，再将这个Channel的OP_READ
        // 注册到Selector中.
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){

            // 通过调用select方法,阻塞地等待channle I/O 可操作
            if(selector.select(TIMEOUT) == 0){
                System.out.println(".");
                continue;
            }

            // 获取I/O操作就绪的SelectionKey,通过SelectionKey可以知道哪些Channle的哪类I/O操作已经就绪;
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();

            while (keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();

                // 当获取一个SelectionKey后，就要将它删除，表示我们已经对这个IO事件进行了处理；
                keyIterator.remove();

                if(key.isAcceptable()){
                    SocketChannel socketChannel =((ServerSocketChannel)key.channel()).accept();
                    socketChannel.configureBlocking(false);

                }
            }
        }
    }
}
