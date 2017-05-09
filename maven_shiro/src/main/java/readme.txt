项目采用maven + springMVC +mybatis+MySQL
注意事项
整个项目已登入为主：1.首先登入页面index.jsp发送login登入请求。
2.然后该请求最初经过Web.xml文件进行拦截在解析一系列的配置文件----->在到UserController控制器---->执行对应的dologin()----->loginUser()---->shiroLogin()  UsernamePasswordToken对象----->在丢给Shiro框架中的Subject对象的--->subject.login(token);
  方法验证用户角色。
 3.就到自定义MyRealm里面执行步骤是首先执行doGetAuthenticationInfo()方法获取shiro框架 中保存的用户名String userName=(String)token.getPrincipal();------->根据用户名查询此用户            
  是否存在User user=userService.getByUserName(userName);if判断如果存在就在执行----->
         doGetAuthorizationInfo()方法为当限前登录的用户授予角色和权。
4.最终验证次用户是否拥有此角色、此权限，操作登入。