package io.netty.example.lishuai.nio;



import java.io.IOException;

/**
 * Created by lishuai on 2017/6/17.
 */
public class TimeServer {

    public static void main(String[] ars) throws IOException {
        int port = 8080;
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer,"MultiplexerTimeServer-001").start();
    }
}
