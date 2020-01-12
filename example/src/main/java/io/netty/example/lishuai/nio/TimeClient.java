package io.netty.example.lishuai.nio;

/**
 * Created by lishuai on 2017/6/17.
 */
public class TimeClient {
    public static void main(String[] ars) {
        int port = 8080;
       new Thread(new TimeClientHandle("127.0.0.1",port),"TimeClientHandle-001").start();
    }
}
