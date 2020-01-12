package io.netty.example.lishuai.nettyv1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LoggingHandler;

import java.nio.charset.Charset;

/**
 * @program: netty-parent
 * @author: lishuai
 * @create: 2019-07-02 09:31

1、netty是怎么创建和初始化了服务端Channel；
2、netty怎么将服务端Channel注册到Selector上；
3、netty怎么绑定的端口。
 */
public class TestNettyServerStart {
    public static void main(String[] args) {
        // 1、实例化服务端启动入口
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        // 2、创建Reactor主线程程，用于接收客户端连接
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        // 3、创建Reactor从线程池，用于SocketChannel的读写和异常操作
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        // 4、设置Reactor主从线程池
        serverBootstrap.group(bossGroup, workerGroup)
                // 5、指定服务端通道类型NioServerSocketChannel
                .channel(NioServerSocketChannel.class)
                // 6、设置NioServerSocketChannel的TCP连接参数
                .option(ChannelOption.SO_BACKLOG, 1024)
                // 7、设置NioSocketChannel的TCP连接参数
                .childOption(ChannelOption.TCP_NODELAY, true)
                // 8、设置NioServerSocketChannel的处理器
                .handler(new LoggingHandler())
                // 9、设置NioSocketChannel的处理器
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
                        channel.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                                // 打印客户端的消息
                                System.out.println(msg);
                            }
                        });
                    }
                });
        try {
            // 10、绑定端口
            ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    System.out.println("服务端启动成功!");
                }
            });
            // 11、一直阻塞直到Server socket 关闭
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 12、优雅关闭线程池
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
