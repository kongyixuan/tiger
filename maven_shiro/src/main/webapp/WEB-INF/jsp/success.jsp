<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>My JSP 'success.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head> 
  <body>
  <shiro:principal>${successMgs}</shiro:principal>
  <shiro:hasAnyRoles name="admin">
   <a href="/jsp/admin.jsp">admin Page</a>  
  </shiro:hasAnyRoles>
  <shiro:hasAnyRoles name="user">
    <a href="/jsp/user.jsp">User Page</a>  
  </shiro:hasAnyRoles>
 <!-- 验证当前用户是否拥有指定权限。 -->
<shiro:hasPermission name="user:create">  
    <a href="createUser.jsp">Create a new User</a>  
</shiro:hasPermission>
<!-- 验证当前用户是否为“访客”，即未认证（包含未记住）的用户。 -->
<shiro:guest>  
Hi there!  Please <a href="login.jsp">Login</a> or <a href="signup.jsp">Signup</a> today!  
</shiro:guest>
  </body>
</html>
