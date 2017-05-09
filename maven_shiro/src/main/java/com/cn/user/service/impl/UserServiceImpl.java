package com.cn.user.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.entity.User;
import com.cn.user.dao.UserDao;
import com.cn.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao<User> userDao;
	@Override
	public boolean doUserLogin(User user) {
		List<User> list = userDao.selectId(user.getUserName());
		if (list.size() == 0) {
			return false;
		} else {
			if (list.get(0).getPassword().equals(user.getPassword())) {
				return true;
			} else {
				return false;
			}
		}
	}
	@Override
	public User getByUserName(String userName) {
		// TODO Auto-generated method stub
		return  userDao.getByUserName(userName);
	}
	@Override
	public Set<String> getRoles(String userName) {
		// TODO Auto-generated method stub
		return userDao.getRoles(userName);
	}
	@Override
	public Set<String> getPermissions(String userName) {
		// TODO Auto-generated method stub
		return userDao.getPermissions(userName);
	}
}
