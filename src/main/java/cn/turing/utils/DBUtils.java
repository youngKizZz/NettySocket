package cn.turing.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
/**
 *mysql数据库连接
 * @author TDS
 *
 */
public class DBUtils {
	//1.获得配置参数
	static String driverClassName;
	static String url;
	static String userName;
	static String password;
	static int initSize;
	static int maxActive;

	static BasicDataSource bs=null;
	static{
		Properties cfg=new Properties();
		InputStream inStream=DBUtils.class
				.getClassLoader()
				.getResourceAsStream("db.properties");
		try {
			cfg.load(inStream);
			driverClassName=cfg.getProperty("jdbc.className");
			url=cfg.getProperty("jdbc.url");
			userName=cfg.getProperty("jdbc.user");
			password=cfg.getProperty("jdbc.password");
			initSize=Integer.parseInt(cfg.getProperty("initSize"));
			maxActive=Integer.parseInt(cfg.getProperty("maxActive"));
			bs=new BasicDataSource();
			bs.setDriverClassName(driverClassName);
			bs.setUrl(url);
			bs.setUsername(userName);
			bs.setPassword(password);
			bs.setInitialSize(initSize);
			bs.setMaxActive(maxActive);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//2.获得连接对象的方法
	public static Connection getConnection() throws SQLException{
		Connection conn= bs.getConnection();
		return  conn;
	}
	//3.归还连接对象
	public static void closeConnection(Connection conn){
		if (conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	//4.rollback方法
	public static void rollBack(Connection conn){
		if (conn!=null) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
}
