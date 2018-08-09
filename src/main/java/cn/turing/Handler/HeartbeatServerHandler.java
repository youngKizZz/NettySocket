package cn.turing.Handler;


import org.apache.log4j.Logger;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 用来处理超时时，发送心跳
 * @author TDS
 *
 */
public class HeartbeatServerHandler extends ChannelInboundHandlerAdapter {
	private static Logger logger = Logger.getLogger(HeartbeatServerHandler.class);
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
            throws Exception {

        if (evt instanceof IdleStateEvent) { 
            IdleStateEvent event = (IdleStateEvent) evt;  
            
            if (event.state() == IdleState.READER_IDLE) {
            	throw new Exception("idle exception");             
            }                         
            logger.error("超时的客户端地址："+ctx.channel().remoteAddress());
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
