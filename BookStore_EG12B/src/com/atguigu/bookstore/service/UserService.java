package com.atguigu.bookstore.service;

import com.atguigu.bookstore.bean.User;

/**
 * 用户处理相关的业务方法
 * @author Administrator
 *
 */
public interface UserService {
	/**
	 * 处理登录请求的业务方法
	 * @param user
	 * @return
	 */
	User login(User user);
	/**
	 * 处理注册请求的业务方法
	 * @param user
	 * @return
	 */
	boolean regist(User user);
	
	User loginCheck(String username);
	
}
