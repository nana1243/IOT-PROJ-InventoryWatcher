package top.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import top.frame.Biz;
import top.model.User;

@Controller
public class UserController {

	@Resource(name = "ubiz")
	Biz<String, User> ubiz;

	@RequestMapping("/login.top")
	public ModelAndView login(HttpServletRequest request) {

		System.out.println("entered login.top");
		ModelAndView mv = new ModelAndView();
		mv.addObject("center", "../user/login");
		mv.setViewName("main/main");
		return mv;
	}

	@RequestMapping("/logout.top")
	public String logout(HttpServletRequest request, ModelAndView mv) {
		HttpSession session = request.getSession();
		if (session != null) {
			session.invalidate();
		}
		return "redirect:main.top";
	}

	@RequestMapping("/loginimpl.top")
	public String loginimpl(HttpServletRequest request, ModelAndView mv) {
		
		String u_id = request.getParameter("ID").trim();
		String u_pwd = request.getParameter("password").trim();	
		User dbuser = null;
		try {
			dbuser = ubiz.get(u_id);
			System.out.println(dbuser);
			if (dbuser.getU_id() != null) {
				if (dbuser.getU_pwd().equals(u_pwd)) {
					System.out.println("dbuser : " + dbuser.getU_id());
					HttpSession session = request.getSession();
					session.setAttribute("loginid", u_id);
					System.out.println("id 다름");
				} else {
					System.out.println("pwd 다름");
				}
			}
		} catch (Exception e) {
			System.out.println("id다름----- ");
			return "redirect:main.top";

		}

		return "redirect:main.top";
	}
	
	
	// register 
	
	@RequestMapping("/signup.top")
	public ModelAndView signup(ModelAndView mv) {
		mv.addObject("center", "../user/register");
		mv.setViewName("main/main");
		return mv;
	}
	
	
	
	
	
	

	
	@RequestMapping(value="/signupimpl.top" ,produces="text/plain;charset=UTF-8")

	public String signupimpl(HttpServletRequest request) {
		User insert_user = new User();
		ArrayList<String> u_addr =null;
		try {
			request.setCharacterEncoding("UTF-8"); 
					

			Enumeration<String> params = request.getParameterNames();
			System.out.println("----------------------------");
			
			
			while (params.hasMoreElements()) {
				String name = (String) params.nextElement();
				if (name.startsWith("address")) {
					u_addr.add(request.getParameter(name));
			
				}	
			}
			System.out.println("----------------------------");			
			
			String u_name = request.getParameter("name").trim();
			String u_id = request.getParameter("ID").trim();
			String u_pwd = request.getParameter("passwordsignin").trim();
			String u_phone = request.getParameter("phonenumber").trim();
		
			String u_address = request.getParameter("address0").trim();
			
			System.out.println("check 5");
			System.out.println(u_address);
			
			try {
				insert_user.setU_id(u_id);
				insert_user.setU_name(u_name);
				insert_user.setU_pwd(u_pwd);
				insert_user.setU_phone(u_phone);
				insert_user.setU_addr(u_addr);
				ubiz.register(insert_user);
				
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("----------SQL삽입실패");
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			System.out.println("ENCODING FAIL");
			e1.printStackTrace();
			
		}
		
				
	
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main/main");
		return "redirect:main.top";	
}

	
	

}
