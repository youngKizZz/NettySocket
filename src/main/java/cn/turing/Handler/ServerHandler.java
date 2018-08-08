package cn.turing.Handler;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;



import cn.turing.Config.NettyConfig;
import cn.turing.bean.Air;
import cn.turing.bean.Group;
import cn.turing.bean.PMS200C;
import cn.turing.jdbc.DBUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {    
	private static Logger logger = Logger.getLogger(ServerHandler.class);
	private Air air = new Air();
	private Group group = new Group();
	private PMS200C pms200c=new PMS200C();
	static Date date = new Date();
	private int counter;
	
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
    		System.err.println("server receive order:"+line+"the counter is:"+ ++counter);
    		logger.info("server receive order:"+line+"the counter is:"+ ++counter);
    		
    		
    		jsonStr(line);
//    	//服务端使用这个就能向 每个连接上来的客户端群发消息
//    	NettyConfig.group.writeAndFlush(info);
//    	Iterator<Channel> iterator = NettyConfig.group.iterator();
//    	while(iterator.hasNext()){
//    		//打印出所有客户端的远程地址
//    		System.out.println((iterator.next()).remoteAddress());
//    	}
  	//单独回复客户端信息
 //  	channelHandlerContext.writeAndFlush(info);
    }
    /**
     * 解析json字符串,插入mysql
     * @param line
     */
    public void jsonStr(String line) {
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
             pms200c.setCfpm10nd(pms200cJson.getInteger("cfpm10nd"));
             pms200c.setGs5um(pms200cJson.getInteger("gs5um"));
             pms200c.setGs25um(pms200cJson.getInteger("gs2.5um"));
             pms200c.setCfpmnd10(pms200cJson.getInteger("cfpm1.0nd"));
             pms200c.setCfpm25nd(pms200cJson.getInteger("cfpm2.5nd"));
             pms200c.setDqpm10nd(pms200cJson.getInteger("dqpm10nd"));
             pms200c.setDqpm25nd(pms200cJson.getInteger("dqpm2.5nd"));
             pms200c.setDqpmnd10(pms200cJson.getInteger("dqpm1.0nd"));
             pms200c.setGs03um(pms200cJson.getInteger("gs0.3um"));
             pms200c.setGs05um(pms200cJson.getInteger("gs0.5um"));
             pms200c.setGs1um(pms200cJson.getInteger("gs1um"));
             pms200c.setGs10um(pms200cJson.getInteger("gs10um"));
             System.out.println("YW:" + this.air.getYW());
             System.out.println("cfpm10nd:" + this.pms200c.getCfpm10nd());
             ServerHandler.push2Mysql(air, pms200c);
             System.err.println("空气检测数据入库成功1");
             logger.info("空气检测数据入库成功1");
         }

         if (line.startsWith("{") && line.endsWith("}") && line.contains("volt")) {
             group.setVolt(json.getInteger("volt"));
             group.setType(json.getString("Type"));
             group.setMark(json.getString("Mark"));
             group.setNode(json.getInteger("Node"));
             group.setPerson(json.getInteger("Person"));
             System.out.println("Type:" + json.getString("Type"));
             ServerHandler.push1Mysql(group);
             System.err.println("红外设备入库成功2");
             logger.info("红外设备入库成功2");
         }
     
    	} catch (Exception var18) {
    		var18.printStackTrace();
    } 

   

}
		/**
		 * 红外探测
		 * @param group
		 */
		public static void push1Mysql(Group group) {
			Connection conn=null;
			PreparedStatement pstmt=null;
			try {
				 conn=DBUtils.getConnection();	
				 conn.setAutoCommit(false);				 
				 String sql = "INSERT INTO infrared_detection (volt,type,mark,node,person,addTime) VALUES(?,?,?,?,?,?)";				 
				 pstmt=conn.prepareStatement(sql);
				// int count=0;
				 pstmt.setInt(1, group.getVolt());
				 pstmt.setString(2, group.getType());
				 pstmt.setString(3, group.getMark());
				 pstmt.setInt(4, group.getNode());
				 pstmt.setInt(5, group.getPerson());
				 pstmt.setTimestamp(6, new Timestamp(date.getTime()));
				 pstmt.executeUpdate();
				// pstmt.addBatch();
//				 if(count %1000==0) {
//					 pstmt.executeBatch();	
//					 
//				 }
				 conn.commit();
				 
			} catch (SQLException e) {
				logger.error(e);
			
				e.printStackTrace();
			}finally {
				DBUtils.closeConnection(conn);
				  if (pstmt != null) {
		                try {
		                    pstmt.close();
		                } catch (SQLException var20) {
		                    var20.printStackTrace();
		                }
		            }
			}
		}
		/**
		 * 空气检测    
		 * @param air
		 * @param pms200c
		 */
		public static void push2Mysql(Air air, PMS200C pms200c) {
			Connection conn=null;
			PreparedStatement pstmt=null;
			 try {
				conn=DBUtils.getConnection();
				conn.setAutoCommit(false);
				String sql = "INSERT INTO air_monitoring (wd,type,mark,node,yw,sd,cfpm10nd,gs5um,gs2_5um,cfpm1_0nd,gs10um,cfpm2_5nd,dqpm10nd,dqpm2_5nd,dqpm1_0nd,gs0_3um,gs0_5um,gs1um,addTime) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstmt=conn.prepareStatement(sql);
				pstmt.setFloat(1, (float) air.getWD());
				pstmt.setString(2, air.getType());
				pstmt.setString(3, air.getMark());
				pstmt.setInt(4, air.getNode());
				pstmt.setInt(5, air.getYW());
				pstmt.setFloat(6, (float) air.getSD());
				pstmt.setInt(7, pms200c.getCfpm10nd());
				pstmt.setInt(8, pms200c.getGs5um());
				pstmt.setInt(9, pms200c.getGs25um());
				pstmt.setInt(10, pms200c.getCfpmnd10());
				pstmt.setInt(11, pms200c.getGs10um());
				pstmt.setInt(12, pms200c.getCfpm25nd());
				pstmt.setInt(13, pms200c.getDqpm10nd());
				pstmt.setInt(14, pms200c.getDqpm25nd());
				pstmt.setInt(15, pms200c.getDqpmnd10());
				pstmt.setInt(16, pms200c.getGs03um());
				pstmt.setInt(17, pms200c.getGs05um());
				pstmt.setInt(18, pms200c.getGs1um());
				pstmt.setTimestamp(19, new Timestamp(date.getTime()));
				conn.commit();
			 } catch (SQLException e) {
				logger.error(e);
				DBUtils.rollBack(conn);
				e.printStackTrace();
			}finally {
				DBUtils.closeConnection(conn);
				if (pstmt != null) {
		                try {
		                    pstmt.close();
		                } catch (SQLException var20) {
		                    var20.printStackTrace();
		                }
		            }
			}
		}
}

