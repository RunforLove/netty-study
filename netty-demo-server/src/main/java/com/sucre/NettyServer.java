package com.sucre;

/**
 * Hello world!
 *
 */
public class NettyServer {
    public static void main( String[] args ) throws Exception{
        ServerHandler serverHandler = new ServerHandler();
        serverHandler.start(8000);
    }
}
