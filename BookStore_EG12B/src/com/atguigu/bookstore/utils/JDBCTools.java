package com.atguigu.bookstore.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JDBCTools {

	
private static DataSource source;
	
	//通过静态代码块初始化数据库连接池
	static {
		//准备数据库连接池需要的参数
		Properties properties = new Properties();
		//加载properties文件到内存中
		InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
		try {
			properties.load(is);
			source = DruidDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//ThreadLocal来给线程对象绑定数据
	private static ThreadLocal<Connection> local= new ThreadLocal<>();
	
	
	
	
	/**
	 * 获取数据库连接的方法
	 * @return
	 */
	public static Connection getConn() {
		Connection conn = local.get();
		try {
			//conn = source.getConnection();
			if(conn==null) {
				conn = source.getConnection();
				local.set(conn);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	
	public static void closeConn() {
		Connection conn = local.get();
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		local.remove();
	}
}
