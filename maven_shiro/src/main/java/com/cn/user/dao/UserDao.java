package com.cn.user.dao;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.cn.entity.User;

@Repository
public interface UserDao<T> {
	List<User> selectId(String username);
	/** 
     * 通过用户名查询用户 
     * @param userName 
     * @return 
     */  
    public User getByUserName(String userName);  
      
    /** 
     * 通过用户名查询角色信息 
     * @param userName 
     * @return 
     */  
    public Set<String> getRoles(String userName);  
      
    /** 
     * 通过用户名查询权限信息 
     * @param userName 
     * @return 
     */  
    public Set<String> getPermissions(String userName);  
}
