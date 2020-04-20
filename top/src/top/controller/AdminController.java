package top.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class AdminController {
	
		
	// adminpage
	
	@RequestMapping("/admin.top")
	public ModelAndView admin(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("center", "../user/admin");
		mv.setViewName("main/main");
		return mv;
	}
	
	
	
	// admin 신청완료 page
	
	




}


