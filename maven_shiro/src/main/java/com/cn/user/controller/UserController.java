package com.cn.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.entity.User;
import com.cn.user.service.impl.UserServiceImpl;
@Controller
@RequestMapping("/user")
public class UserController {
@Autowired
private UserServiceImpl userServiceImpl;
@RequestMapping("/dologin.do") //url  
public String dologin(User user, Model model){  
    if(userServiceImpl.doUserLogin(user)){  
        model.addAttribute("successMsg", "登陆成功！");//返回到页面所夹带的参数  
        model.addAttribute("name", user.getUsername());  
        return "/success";//返回的页面  
    }else{  
        model.addAttribute("failMsg", "用户不存在或密码错误！");  
        return "/fail";  
    }     
}  
}
