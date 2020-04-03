package com.newer.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.newer.domain.User;
import com.newer.dto.MsgDto;
import com.newer.service.UserService;
import com.newer.util.MyConstants;

@RestController
@RequestMapping("login")
//将名字为：MyConstants.SESSSION_USER的属性同时绑定到session中
@SessionAttributes(MyConstants.SESSSION_USER)
public class LoginController {
	@Autowired
	private UserService userService;
   
	@RequestMapping("login")
	public User login(String uname,String upwd,Model model,HttpSession session) {
		User user=this.userService.login(uname, upwd);
		if (user!=null) {
			model.addAttribute(MyConstants.SESSSION_USER, user); 
		}
		return user;
	}
	
	 
	@RequestMapping("logout")
	public MsgDto  logout(HttpSession session,SessionStatus sessionStatus) {
		session.invalidate();
		sessionStatus.setComplete();
		return new MsgDto(true, "注销成功");
	}
	
	@RequestMapping("findSesUser")
	public User findSesUser(Model model) {
		User user=(User)model.getAttribute(MyConstants.SESSSION_USER);
		return user;		
	}
	
}
