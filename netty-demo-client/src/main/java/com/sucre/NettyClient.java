package com.sucre;

import com.sun.deploy.util.SessionState;

/**
 * Hello world!
 *
 */
public class NettyClient {
    public static void main( String[] args ) throws Exception{
        ClientHandler clientHandler = new ClientHandler();
        clientHandler.connect("127.0.0.1", 8000);
    }
}
