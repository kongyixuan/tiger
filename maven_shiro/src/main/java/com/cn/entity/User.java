package com.cn.entity;

public class User {
	private  Integer id;
	private String userName;
	private String password;
    private String roleId;
	public String getRoleId() {
	return roleId;
}

public void setRoleId(String roleId) {
	this.roleId = roleId;
}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
