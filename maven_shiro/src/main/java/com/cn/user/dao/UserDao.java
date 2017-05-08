package com.cn.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cn.entity.User;

@Repository
public interface UserDao<T> {
	List<User> selectId(String username);
}
