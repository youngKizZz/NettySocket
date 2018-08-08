package cn.turing.Handler;

import org.apache.ibatis.session.SqlSession;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

import cn.turing.Config.NettyConfig;
import cn.turing.Mapper.DataMapper;
import cn.turing.bean.Air;
import cn.turing.bean.Group;
import cn.turing.utils.SqlSessionFactoryUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
/**
 * 处理业务逻辑
 * @author TDS
 *
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {    
	private static Logger logger = Logger.getLogger(ServerHandler.class);
	private Air air = new Air();
	private Group group = new Group();
	private Integer count;
	SqlSession	sqlSession=null;
		  	
	/**
     * 客户端与服务端创建连接的时候调用
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端与服务端连接开始...");
        logger.info("客户端与服务端连接开始...");
        NettyConfig.group.add(ctx.channel());
    }
 
    /**
     * 客户端与服务端断开连接时调用
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端与服务端连接关闭...");
        logger.info("客户端与服务端连接关闭...");
        
        NettyConfig.group.remove(ctx.channel());
    }
 
    /**
     * 服务端接收客户端发送过来的数据结束之后调用
     */
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        System.out.println("信息接收完毕...");
        logger.info("信息接收完毕...");
    }
 
    /**
     * 工程出现异常的时候调用
     */
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
 
    /**
     * 服务端处理客户端websocket请求的核心方法，这里接收了客户端发来的信息
     */
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object info) throws Exception {
    	
    		String line = (String) info;
    		System.err.println("server receive order:"+line);
    		logger.info("server receive order:"+line);    		   
    		parsingJson(line);
//    	//服务端使用这个就能向 每个连接上来的客户端群发消息
//    	NettyConfig.group.writeAndFlush(info);
//    	Iterator<Channel> iterator = NettyConfig.group.iterator();
//    	while(iterator.hasNext()){
//   		//打印出所有客户端的远程地址
//   		System.out.println("客户端的远程地址"+(iterator.next()).remoteAddress());
//   		logger.info("客户端的远程地址"+(iterator.next()).remoteAddress());
//    	}
  	//单独回复客户端信息
 //  	channelHandlerContext.writeAndFlush(info);
    }
    /**
     * 解析json字符串,插入mysql
     * @param line
     */
    public void parsingJson(String line) {
    	try {
    	 JSONObject json = JSONObject.parseObject(line);
         System.out.println("json:" + json);
         if (line.startsWith("{") && line.endsWith("}") && line.contains("PMS200C")) {
             air.setWD(json.getFloat("WD"));
             air.setType(json.getString("Type"));
             air.setMark(json.getString("Mark"));
             air.setNode(json.getInteger("Node"));
             air.setYW(json.getInteger("YW"));
             air.setSD(json.getFloat("SD"));              
             System.out.println("sd:" + this.air.getSD());
             JSONObject pms200cJson = json.getJSONObject("PMS200C");
             air.setCfpm10nd(pms200cJson.getInteger("cfpm10nd"));
             air.setGs5um(pms200cJson.getInteger("gs5um"));
             air.setGs25um(pms200cJson.getInteger("gs2.5um"));
             air.setCfpmnd10(pms200cJson.getInteger("cfpm1.0nd"));
             air.setCfpm25nd(pms200cJson.getInteger("cfpm2.5nd"));
             air.setDqpm10nd(pms200cJson.getInteger("dqpm10nd"));
             air.setDqpm25nd(pms200cJson.getInteger("dqpm2.5nd"));
             air.setDqpmnd10(pms200cJson.getInteger("dqpm1.0nd"));
             air.setGs03um(pms200cJson.getInteger("gs0.3um"));
             air.setGs05um(pms200cJson.getInteger("gs0.5um"));
             air.setGs1um(pms200cJson.getInteger("gs1um"));
             air.setGs10um(pms200cJson.getInteger("gs10um"));
             System.out.println("YW:" +air.getYW());
             System.out.println("cfpm10nd:" + air.getCfpm10nd());
            // ServerHandler.push2Mysql(air, pms200c);
         	try {
         		sqlSession=SqlSessionFactoryUtil.openSession();
         		DataMapper dataMapper=sqlSession.getMapper(DataMapper.class);
				count	=dataMapper.insertAirAndPms200C(air);
				System.out.println("受影响的行数："+count);
					sqlSession.commit();
				} catch (Exception e) {					
					e.printStackTrace();
					logger.info(e);
				}finally {
					if(sqlSession != null) {
						sqlSession.close();
					}
				}
             logger.info("空气检测数据入库成功1");
         }

         if (line.startsWith("{") && line.endsWith("}") && line.contains("volt")) {
             group.setVolt(json.getInteger("volt"));
             group.setType(json.getString("Type"));
             group.setMark(json.getString("Mark"));
             group.setNode(json.getInteger("Node"));
             group.setPerson(json.getInteger("Person"));
             System.out.println("Type:" + json.getString("Type"));           
          		try {
          			sqlSession=SqlSessionFactoryUtil.openSession();
          			DataMapper dataMapper=sqlSession.getMapper(DataMapper.class);
				count=	dataMapper.insertGroup(group);
				System.out.println("受影响的行数："+count);
					sqlSession.commit();
				} catch (Exception e) {					
					e.printStackTrace();
					logger.error(e);
				}finally {
					if(sqlSession != null) {
						sqlSession.close();
					}
				}         		
             System.err.println("红外设备入库成功2");
             logger.info("红外设备入库成功2");
         }
    	} catch (Exception var18) {
    		var18.printStackTrace();
    		logger.error(var18);
    } 

    }	
}

