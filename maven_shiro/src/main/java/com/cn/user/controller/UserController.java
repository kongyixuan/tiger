package com.cn.user.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.entity.User;
import com.cn.user.service.impl.UserServiceImpl;
@Controller
@RequestMapping("/user")
public class UserController {

/*private UserServiceImpl userServiceImpl;*/
@RequestMapping("/dologin.do") //url  
public String dologin(User user, Model model){ 
	String userInfo=loginUser(user);
	if("success".equals(userInfo)){
		model.addAttribute("successMsg", "shiro登录成功");
		model.addAttribute("userName",user.getUserName());
		return "/success";
	}else{
		model.addAttribute("failMsg","用户名不存在或者密码不对");
             return "/fail";
	}
  /*  if(userServiceImpl.doUserLogin(user)){  
        model.addAttribute("successMsg", "登陆成功！");//返回到页面所夹带的参数  
        model.addAttribute("name", user.getUsername());  
        return "/success";//返回的页面  
    }else{
    	String pwd=user.getPassword();
        model.addAttribute("failMsg", "用户不存在或密码错误！");  
        return "/fail";  
    }  */   
}
private String loginUser(User user){
	if(isReLogin(user)){
		return "success";
	}
	return shiroLogin(user);
	
}
/*这个方法是将用户信息丢给shiro框架 ---框架根据*/
private String shiroLogin(User user){
	/*将用户的信息传入到shiro框架*/
	UsernamePasswordToken uToken=new UsernamePasswordToken(user.getUserName(),user.getPassword(),"");
	uToken.setRememberMe(true);
	try {
		/*调用框架中的登录方法*/
		SecurityUtils.getSubject().login(uToken);
	} catch (Exception e) {
	e.printStackTrace();
	return "内部出错";
	}
	return "success";	
}

public void  loginOut(HttpServletRequest request,HttpServletResponse response) throws IOException{
	Subject subject=SecurityUtils.getSubject();
	if(subject!=null){
		try {
			subject.logout();
		} catch (Exception e) {

		}
	}
	response.sendRedirect("/index");
}
/*判断是否登录了*/
private boolean isReLogin(User user){
	Subject subject=SecurityUtils.getSubject();
	if(subject.isAuthenticated()){
	    return true; // 参数未改变，无需重新登录，默认为已经登录成功  
	}
	return false;
}
}
