package com.atguigu.bookstore.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.atguigu.bookstore.utils.JDBCUtils;

/**
 * 封装对数据库数据操作的基本方法
 * 
 *
 */
public class BaseDao {
	//创建QueryRunner对象
	private QueryRunner runner = new QueryRunner();
	
	/**
	 * 增删改方法
	 * 	- 凡是不能确定的数据都是用参数传递
	 */
	public int update(String sql , Object...params) {
		Connection conn = JDBCUtils.getConn();
		int i = 0;
		try {
			i = runner.update(conn, sql, params);
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			//释放数据库连接
			//JDBCUtils.closeConn(conn);
		}
		// 如果i>0代表增删改成功，<=0代表失败
		return i;
	}
	/**
	 * 查询一条记录 封装为一个对象
	 */
	public <T>T getBean(Class<T> type, String sql , Object...params) {
		Connection conn = JDBCUtils.getConn();
		T t = null;
		// 参数1：数据库连接 ， 参数2：sql语句 ， 参数4：sql语句占位符参数列表 ， 参数3：查询结果解析工具类对象[type就是讲查询到记录封装对象的类型  ]
		try {
			t = runner.query(conn, sql, new BeanHandler<>(type), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			//JDBCUtils.closeConn(conn);
		}
		//如果t==null代表查询失败 ， 如果不为null代表查询成功
		return t;
	}
	/**
	 * 查询多条记录封装为对象的集合
	 */
	public <T>List<T> getBeanList(Class<T> type , String sql , Object...params) {
		Connection conn = JDBCUtils.getConn();
//		System.out.println(conn);
		List<T> list = null;
		try {
			list = runner.query(conn, sql, new BeanListHandler<>(type), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			//JDBCUtils.closeConn(conn);
		}
		return list;
	}
	/**
	 * 查询第一行第一列的数据封装为Object
	 */
	public Object getScalar(String sql , Object...params) {
		Connection conn = JDBCUtils.getConn();
		Object object = null;
		try {
			//查询第一行第一列的数据并提升为Object对象返回
			object = runner.query(conn, sql, new ScalarHandler(), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			//JDBCUtils.closeConn(conn);
		}
		return object;
	}
	/**
	 * 批量增删改的方法
	 * @param sql
	 * @param params
	 */
	public void batchUpdate(String sql , Object[][] params) {
		Connection conn = JDBCUtils.getConn();
		//参数1：数据库连接， 参数2：批处理执行的sql语句 ， 参数3：二维数组[第一维 批处理执行的次数 ， 第二维每次执行批处理时sql需要的参数列表]
		try {
			runner.batch(conn, sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			//JDBCUtils.closeConn(conn);
		}
	}
	
}
