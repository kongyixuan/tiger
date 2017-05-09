package com.cn.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.cn.entity.User;
import com.cn.user.service.impl.UserServiceImpl;

public class ShiroDbRealm extends AuthorizingRealm {
@Autowired
private UserServiceImpl userServiceImpl;

	public UserServiceImpl getUserServiceImpl() {
	return userServiceImpl;
}
public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
	this.userServiceImpl = userServiceImpl;
}
/*授予用户角色权限*/
/*后执行*/
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		/*获取登录用户*/
		String userName=(String)principalCollection.getPrimaryPrincipal();
		SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
		/*根据用户名查询角色*/
		simpleAuthorizationInfo.setRoles(userServiceImpl.getRoles(userName));
		System.out.print(userServiceImpl.getRoles(userName));
		/*根据用户名查询权限*/
		simpleAuthorizationInfo.setStringPermissions(userServiceImpl.getPermissions(userName));
		System.out.print(userServiceImpl.getPermissions(userName));
		/*User user=(User)SecurityUtils.getSubject().getSession().getAttribute("user");
		SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addRole("");*/
		/*返回一个字符串集合*/
		return simpleAuthorizationInfo;
	}
	/*首先执行doGetAuthenticationInfo()方法*/
	/*验证当前登录的用户*/
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken aToken) throws AuthenticationException {
/*获取controller中传来的用户信息*/
		String userName=(String)aToken.getPrincipal();
//		判断用户是否存在
		User user =userServiceImpl.getByUserName(userName);
		/*用户存在doGetAuthorizationInfo()方法执行进行权限角色验证*/
		if(null!=user){
			AuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(), "");
			return authenticationInfo;
		}
		return null;
	}

}
