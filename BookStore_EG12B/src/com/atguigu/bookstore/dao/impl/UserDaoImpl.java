package com.atguigu.bookstore.dao.impl;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.UserDao;

public class UserDaoImpl extends BaseDao implements UserDao{

	@Override
	public User getUserByUsernameAndPassword(User user) {
		// 参数、 操作的表
		String sql = "SELECT id , username , password , email "
				+ "  FROM bs_user WHERE username = ? AND password = ?";
		return getBean(User.class, sql, user.getUsername() , user.getPassword());//调用BaseDao的方法实现数据库的操作
	}

	@Override
	public int saveUser(User user) {
		String sql = "INSERT INTO bs_user(username , password , email) VALUES(? , ? ,?)";
		return update(sql, user.getUsername() , user.getPassword() , user.getEmail());
	}

	@Override
	public User getUserByUsername(String username) {
		String sql = "SELECT id , username , password , email "
				+ "  FROM bs_user WHERE username = ? ";
		 return getBean(User.class, sql, username);
	}

}
