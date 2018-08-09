package cn.turing.Handler;




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
	
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
            throws Exception {

        if (evt instanceof IdleStateEvent) { 
            IdleStateEvent event = (IdleStateEvent) evt;  
            
            if (event.state() == IdleState.READER_IDLE) {
            	throw new Exception("idle exception");
            }                         
           
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
