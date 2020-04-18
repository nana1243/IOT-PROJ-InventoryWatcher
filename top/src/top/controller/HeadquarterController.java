package top.controller;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import top.frame.Biz;
import top.vo.ChainVO;
import top.vo.HeadquarterVO;
import top.vo.UserVO;

@Controller
public class HeadquarterController {
	
	
	@Resource(name = "hqbiz")
	Biz<String,HeadquarterVO> hqbiz;
	
	@Resource(name = "ubiz")
	Biz<String,UserVO> ubiz;
	
	
	@Resource(name = "chainbiz")
	Biz<String,ChainVO> chainbiz;
	
	
	/*
	 * 1. register(signup) -headquarter(주체)
	 * 2. registerimpl(signup impl) -headquarter (주체)
	 * 3. addAddress - headquarter(주체)
	 * 4. addAddress impl - headquarter(주체)
	 * */
	
	
	// 1. register	
	@RequestMapping("/signup.top")
	public ModelAndView signup(ModelAndView mv) {
		System.out.println("enter into signup.top");
//		mv.addObject("center", "../user/register");
		mv.setViewName("user/register");
		return mv;
	}

	// 2. register_impl

	@RequestMapping(value = "/signupimpl.top", produces = "text/plain;charset=UTF-8")
	public String signupimpl(HeadquarterVO hq_register, HttpServletRequest request) {
		System.out.println("enter into signupimpl.top");

		try {

			// 한글 안깨지려면!
			request.setCharacterEncoding("UTF-8");

			// getparameter start
			String hq_name = request.getParameter("cname").trim();
			String hq_id = request.getParameter("hqid").trim();
			String hq_pwd = request.getParameter("hqpwd").trim();
			String hq_email = request.getParameter("hqemail").trim();
			String hq_phone = request.getParameter("hqphone").trim();
			String hq_chaincnt = request.getParameter("chaincnt").trim();
			String hq_addr = request.getParameter("caddr").trim();
			String hq_regdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			
			// hq resgister set and register
			try {
				hq_register.setHqID(hq_id);
				hq_register.setHqPwd(hq_pwd);
				hq_register.setHqName(hq_name);
				hq_register.setHqEmail(hq_email);
				hq_register.setHqPhone(hq_phone);
				hq_register.setHqRegDate(hq_regdate);
				hq_register.setChainCount(hq_chaincnt);
				hq_register.setHqAddress(hq_addr);
				hqbiz.register(hq_register);

				// error 날 상황 -> utf encoding안될때, sql exception
			} catch (Exception e) {
				
				// null일때 error남
				System.out.println("----------SQL INSERT FAIL ----------------");
			}
		} catch (UnsupportedEncodingException e1) {
			System.out.println("----------ENCODING FAIL----------------");
			e1.printStackTrace();
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main/main");
		return "redirect:main.top";
	}
	
	@RequestMapping("/addAddr.top")
	public ModelAndView addAddr(ModelAndView mv, HttpServletRequest request) {
		System.out.println("enter into addAddr.top");

		// addADDR과정
		HttpSession session = request.getSession();
		String hqid = (String) session.getAttribute("loginid");
		System.out.println(hqid);
		
		// headquarter가 담당하는 chain갯수를 jsp로 넘겨줘야 하기에 !
		
		HeadquarterVO hqvo = hqbiz.get(hqid);
		
		Integer chaincnt = Integer.parseInt(hqvo.getChainCount());
		session.setAttribute("chaincnt", chaincnt);
		// 화면으로 넘어가자!
		mv.setViewName("user/AddAddr");
		return mv;
	}

	
	
	// Addrimpl 과정 
	
	//여기서 오류나는 것은 해당되는 주소가 NULL 값일때
	@RequestMapping(value = "/addAddrimpl.top", produces = "text/plain;charset=UTF-8")
	public String addAddrimpl(HttpServletRequest request) {
		ArrayList<String> hq_addr = new ArrayList<String>();
		HttpSession session = request.getSession();
		String hqid = (String) session.getAttribute("loginid");
		String chainname = request.getParameter("chainname").trim();
		String regdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		// 주소는 여러개 이기때문에 아래와 같은 parsing과정이 필요하다

		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String name = (String) params.nextElement();
			if (name.startsWith("address")) {
				String inputaddr = request.getParameter(name);
				inputaddr = inputaddr.replace(",", " ");
				hq_addr.add(inputaddr);
				System.out.println(inputaddr+"----------- check ADDR--------------");
			}
		}
		System.out.println(hq_addr);
		
		// 주소를 입력하는 동시에 chain INSERT 시작된다!
		for (int i = 0; i < hq_addr.size(); i++) {
			System.out.println("check insert");
			ChainVO chain = new ChainVO();
			chain.setChainName(chainname);
			chain.setChainRegDate(regdate);
			chain.setHqID(hqid);
			chain.setChainAddress(hq_addr.get(i));
			
			try{chainbiz.register(chain);}
			catch (Exception e) {
				System.out.println("insert exception!");
				return "redirect:addAddr.top";
			}
			System.out.println("success insert chainvo :" + i + "st");
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main/main");
		return "redirect:main.top";
	}

}
