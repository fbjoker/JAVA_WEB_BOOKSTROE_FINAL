package com.atguigu.bookstore.service.impl;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;
import com.atguigu.bookstore.service.UserService;

public class UserServiceImpl implements UserService {
	//业务层操作数据库 通过dao层实现
	private UserDao dao = new UserDaoImpl();
	@Override
	public User login(User user) {
		return dao.getUserByUsernameAndPassword(user);
	}

	@Override
	public boolean regist(User user) {
		int i=0;
		try {
			i = dao.saveUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i>0;
	}

	@Override
	public User loginCheck(String username) {
		
		return dao.getUserByUsername(username);
	}

}
