package cn.turing.ServerSocket;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;


import cn.turing.Handler.HeartbeatServerHandler;
import cn.turing.Handler.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;


public class Server {
	 	
	private static Logger logger = Logger.getLogger(Server.class);
	private ServerSocketChannel serverSocketChannel;
	 
	public Server(int serverPort){
		bind(serverPort);
	}
	
	private void bind(int serverPort) {
		Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
            	//服务端要建立两个group，一个负责接收客户端的连接，一个负责处理数据传输
				//连接处理group
				EventLoopGroup boss = new NioEventLoopGroup();
				//事件处理group
				EventLoopGroup worker = new NioEventLoopGroup();
				ServerBootstrap bootstrap = new ServerBootstrap();
				// 绑定处理group
				bootstrap.group(boss, worker)
						//实现* NIO选择器基于接受新连接的实现。
						 .channel(NioServerSocketChannel.class)
						//保持连接数
						.option(ChannelOption.SO_BACKLOG, 1024)
						//有数据立即发送
						.option(ChannelOption.TCP_NODELAY, true)
						//保持连接
						.childOption(ChannelOption.SO_KEEPALIVE, true)
						//打印netty框架运行的日志
						.handler(new LoggingHandler(LogLevel.INFO))
						.localAddress(new InetSocketAddress(serverPort))
						//处理新连接
						.childHandler(new ChannelInitializer<SocketChannel>() {
							@Override
							protected void initChannel(SocketChannel sc) throws Exception {
								// 增加任务处理
								ChannelPipeline p = sc.pipeline();								
								p.addLast(		
									//读写超时时间 参数分别为读超时时间，写超时时间，读和写都超时时间，时间单位
									new IdleStateHandler(2,2,4,TimeUnit.MINUTES),
									new HeartbeatServerHandler(),
									//使用了netty自带的编码器和解码器										
									new StringDecoder(Charset.forName("utf-8")),
									new StringEncoder(Charset.forName("utf-8")),									
									//自定义的处理器
									new ServerHandler());								
								
							}
						
						});
						
 
				//绑定端口，同步等待成功,异步操作
				ChannelFuture future;
				try {
					future = bootstrap.bind(serverPort).sync();
					if (future.isSuccess()) {
						serverSocketChannel = (ServerSocketChannel) future.channel();
						System.out.println("服务端开启成功");
						logger.info("服务端开启成功");
					} else {
						System.out.println("服务端开启失败");
						logger.info("服务端开启失败");
					}
					
					//等待服务监听端口关闭,就是由于这里会将线程阻塞，导致无法发送信息，所以我这里开了线程
					future.channel().closeFuture().sync();
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e);
				}
				finally {
					//退出，释放线程池资源
					boss.shutdownGracefully();
					worker.shutdownGracefully();
				}
            }
        });
		thread.start();
	}
	/**
	 * 
	 * @param msg
	 */
	public void sendMessage(Object msg){
		if(serverSocketChannel != null){
			serverSocketChannel.writeAndFlush(msg);
		}
	}
	public static void main(String[] args) {
		System.out.println("start");
		int port;
		if(args.length > 0) {
			port = Integer.parseInt(args[0]);
		}else {
			port=9994;
		}
		Server server=new Server(port);
		server.bind(port);
	}
		
}

