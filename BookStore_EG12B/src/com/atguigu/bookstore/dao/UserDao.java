package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.bean.User;

/**
 * 约束bs_user表的操作方法
 * @author Administrator
 *
 */
public interface UserDao {
	/**
	 * 根据账号密码查询用户对象的方法
	 * @param user
	 * @return
	 */
	User getUserByUsernameAndPassword(User user);
	/**
	 * 保存用户数据到数据库中
	 * @param user
	 * @return
	 */
	int saveUser(User user);
	
	
	User getUserByUsername(String user);
}
