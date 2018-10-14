package com.atguigu.bookstore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;

public class UserDaoTest {
	private UserDao dao = new UserDaoImpl();
	@Test
	public void testSave() {
		//模仿用户注册操作
		//1、获取用户提交的注册参数
		String username = "admin";
		String password = "123456";
		String email = "admin@atguigu.com";
		User user = new User(null, username, password, email);
		//2、调用dao将数据保存到数据库中
		int i = dao.saveUser(user);
		System.out.println(i>0);//?"注册成功":"注册失败"
		
	}
	@Test
	public void testGet() {
		//1、获取用户提交的登录参数
		String username = "admin";
		String password = "123456";
		User user = new User(null, username, password, null);
		//2、调用dao查询数据
		User u = dao.getUserByUsernameAndPassword(user);
		System.out.println(u);
	}

}
