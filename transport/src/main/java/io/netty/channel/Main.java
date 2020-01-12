package io.netty.channel;

/**
 * @program: netty-parent
 * @author: lishuai
 * @create: 2020-01-07 17:25
head:131071
tail:511
 */
public class Main {
    public static void main(String[] args){
        System.out.println("hello netty");

        int head = ChannelHandlerMask.mask(DefaultChannelPipeline.HeadContext.class);
        int tail = ChannelHandlerMask.mask(DefaultChannelPipeline.TailContext.class);
        System.out.println("head:"+head);
        System.out.println("tail:"+tail);


        System.out.println(head & (1 << 9));
        System.out.println(head & (1 << 10));
    }
}
