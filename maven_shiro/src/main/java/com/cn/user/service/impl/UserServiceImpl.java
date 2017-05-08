package com.cn.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.entity.User;
import com.cn.user.dao.UserDao;
import com.cn.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao<User> dao;
	@Override
	public boolean doUserLogin(User user) {
		List<User> list = dao.selectId(user.getUsername());
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
}
