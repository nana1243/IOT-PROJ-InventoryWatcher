package top.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;




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
import top.vo.ChainVO;
import top.vo.HeadquarterVO;
import top.vo.NotiVO;
import top.vo.UserVO;

@Controller
public class UserController {
	
	
	

	@Resource(name = "ubiz")
	Biz<String, UserVO> ubiz;

	@Resource(name = "hqbiz")
	Biz<String, HeadquarterVO> hqbiz;

	@Resource(name = "chainbiz")
	Biz<String, ChainVO> chainbiz;
	
	
	/*
	 * 1.login (완료)
	 * 2.logout( 완료)
	 * 3.logincheck up(완료)
	 * 
	 *  
	 * */
	

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

	// login ID available check up START!!
	// 해당 코드를 CASE로 바꿔볼것 -> 수정사항 1

	@RequestMapping(value = "/logincheckup.top", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String AjaxView(@RequestParam("ID") String id, @RequestParam("password") String pwd,
			@RequestParam("radio") String radio, String hqcheck, String ucheck, HttpServletRequest request)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("enter into login check!");
		String str = "";
		System.out.println("id : " + id);
		System.out.println("password : " + pwd);
		System.out.println(radio);
//		if(radio=="user") {
//			UserVO user = ubiz.get(id);
//		
		try {
			HeadquarterVO hq = hqbiz.get(id);
			UserVO user = ubiz.get(id);

			if (hq != null && user == null) {
				hqcheck = hq.getHqID();
				if (hq.getHqPwd().equals(pwd)) {
					str = "Yes";
				} else {
					str = "NO";
				}
			} else if (hq == null && user != null) {
				if (user.getUserPwd().equals(pwd)) {
					str = "Yes";
				} else {
					str = "NO";
				}

			} else {
				str = "NO";
			}

			// 그이외의 예외 발생을 위해
		} catch (Exception e) {
			str = "NO";
			return str.trim();
		}

		return str;
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
			dbuser = ubiz.get(id);
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

	// user 신청자 받는 page

	@RequestMapping("/apply.top")
	public ModelAndView apply(HttpServletRequest request) {
		System.out.println("entered apply.top");
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		String hqid = (String) session.getAttribute("loginid");
		System.out.println("in apply.top check hqid : " + hqid);
		ArrayList<ChainVO> clist = new ArrayList<ChainVO>();
		clist.addAll(chainbiz.getname(hqid));

		System.out.println(clist);
		System.out.println(clist.size());

//		mv.addObject("center", "../user/apply");
		mv.addObject("clist", clist);
		mv.setViewName("user/apply");
		return mv;
	}

	// 신청자를 받고 나서 admin page에 user의 신청자수를 넘겨라

	@RequestMapping("/applyimpl.top")
	public ModelAndView applyimpl(HttpServletRequest request) {
		System.out.println("entered apply.top");
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("loginid");
		
		// user와 해당되는 chainid를 찾자
		
		String chainid= ubiz.get(userid).getChainID();
		System.out.println(chainid);
//		String cname = request.getParameter("cname");
//		String caddr = request.getParameter("caddr");
		
		Integer user_apply_cnt = Integer.parseInt(request.getParameter("ucnt"));
		System.out.println(user_apply_cnt);
	

		NotiVO noti = new NotiVO(chainid,userid,user_apply_cnt);
		
		
//		mv.addObject("center", "../user/apply");
		mv.setViewName("main/main");
		return mv;
	}

	// show mypage

	// modify mypage

}
