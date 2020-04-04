package top.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class UserController {
	
	
	@RequestMapping("/login.top")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("center", "user/login");
		mv.setViewName("main");
		return mv;
	}
	
	
	


}
