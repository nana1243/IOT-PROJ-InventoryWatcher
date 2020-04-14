package top.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import top.frame.Biz;
import top.vo.HeadquarterVO;
import top.vo.UserVO;

@Controller
public class UserController {

	@Resource(name = "userbiz")
	Biz<String, UserVO> userbiz;

	@Resource(name = "hqbiz")
	Biz<String, HeadquarterVO> hqbiz;

	// login
	@RequestMapping("/login.top")
	public ModelAndView login(HttpServletRequest request) {

		System.out.println("entered login.top");
		ModelAndView mv = new ModelAndView();
		mv.addObject("center", "../user/login");
		mv.setViewName("main/main");
		return mv;
	}

	// logout
	@RequestMapping("/logout.top")
	public String logout(HttpServletRequest request, ModelAndView mv) {
		HttpSession session = request.getSession();
		if (session != null) {
			session.invalidate();
		}
		return "redirect:main.top";
	}

	// loginimpl
	@RequestMapping("/loginimpl.top")
	public String loginimpl(HttpServletRequest request, ModelAndView mv) {

		// step1. form으로 부터 필요한 name을 받아온다
		String id = request.getParameter("ID").trim();
		String pwd = request.getParameter("password");
		String[] radio = request.getParameterValues("radio");

		// step2. 필요한 vo객체를 불러오고 나서 pwd가 일치하는지 확인
		if (radio[0].equals("hq")) {
			HeadquarterVO dbhquser = new HeadquarterVO();
			dbhquser = hqbiz.get(id);
			System.out.println("hquser : " + dbhquser);
			try {
				if (dbhquser.getHqID() != null) {
					if (dbhquser.getHqPwd().equals(pwd)) {
						HttpSession session = request.getSession();
						session.setAttribute("loginid", id);
						session.setAttribute("chaincnt", dbhquser.getChainCount());
						System.out.println("----------- hqid 비번 일치--------------");
					} else {
						System.out.println("---------- hq pwd 일치하지 않음-------------");
					}
				}
			} catch (Exception e) {
				System.out.println("sqlexcetion");
				e.printStackTrace();

			}
		} else {
			UserVO dbuser = null;
			dbuser = userbiz.get(id);
			try {
				if (dbuser.getUserID() != null) {
					if (dbuser.getUserPwd().equals(pwd)) {
						System.out.println("dbuser : " + dbuser.getUserID());
						HttpSession session = request.getSession();
						session.setAttribute("loginid", id);
						
						
						System.out.println("----------- user id 비번 일치--------------");

					} else {
						System.out.println("---------- user pwd 일치하지 않음-------------");
					}
				}
			} catch (Exception e) {
				System.out.println("sqlexcetion ");
				return "redirect:main.top";

			}
		}

		return "redirect:main.top";
	}
	
	
	// idcheckup

	@RequestMapping(value = "/idCheck.top", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String AjaxView(@RequestParam("hqid") String hqid, String idcheck, HttpServletRequest request)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String str = "";
		try {
			System.out.println(hqid);
			idcheck = hqbiz.get(hqid).getHqID();
		} catch (Exception e) {
			System.out.println("중복이 없습니다");
		}
		System.out.println(idcheck);
		if (idcheck == null) { // 이미 존재하는 계정
			str = "YES";
		} else { // 사용 가능한 계정
			str = "NO";
		}
		return str;
	}

	// show mypage

	// modify mypage

}
