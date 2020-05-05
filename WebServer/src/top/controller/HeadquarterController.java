package top.controller;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class HeadquarterController {

	@Resource(name = "hqbiz")
	Biz<String, HeadquarterVO> hqbiz;

	@Resource(name = "ubiz")
	Biz<String, UserVO> ubiz;

	@Resource(name = "chainbiz")
	Biz<String, ChainVO> chainbiz;

	@Resource(name = "notibiz")
	Biz<String, NotiVO> notibiz;

	HeadquarterVO hqvo;
	ChainVO chainvo;
	UserVO usrvo;
	NotiVO notivo;

	/*
	 * 1. register 2. registerimpl 3. addAddr- chain추가 4. addAddrImpl - Chain추가 5.
	 * idCheck - ID 중복확인 기능 5. read(ajax) 6. readTrue(ajax) 7. update 8. updateimpl
	 */

	// 1. register
	@RequestMapping("/signup.top")
	public ModelAndView signup(ModelAndView mv) {
		System.out.println("enter into signup.top");
		mv.setViewName("user/register");
		return mv;
	}

	// 2. register_impl
	@RequestMapping(value = "/signupimpl.top", produces = "text/plain;charset=UTF-8")
	public String signupimpl(HttpServletRequest request) {

		hqvo = new HeadquarterVO();
		try {
			request.setCharacterEncoding("UTF-8"); // 한글 안깨지려면
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
				hqvo.setHqID(hq_id);
				hqvo.setHqPwd(hq_pwd);
				hqvo.setHqName(hq_name);
				hqvo.setHqEmail(hq_email);
				hqvo.setHqPhone(hq_phone);
				hqvo.setHqRegDate(hq_regdate);
				hqvo.setChainCount(hq_chaincnt);
				hqvo.setHqAddress(hq_addr);
				hqbiz.register(hqvo);
			} catch (Exception e) {

				System.out.println("SQL INSERT FAIL"); // null 일때
				return "redirect:main.top";
			}
		} catch (UnsupportedEncodingException e1) {
			System.out.println("----------ENCODING FAIL----------------");
			return "redirect:main.top";
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main/main");
		return "redirect:main.top";
	}

	// sign up 할때 idcheckup
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

	// headquarter가 담당하는 체인갯수만큼 input이 존재해야한다
	
	@RequestMapping("/addAddr.top")
	public ModelAndView addAddr(ModelAndView mv, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String hqid = (String) session.getAttribute("loginId");
		hqvo = hqbiz.get(hqid);
		Integer chaincnt = Integer.parseInt(hqvo.getChainCount());
		session.setAttribute("chaincnt", chaincnt);
		mv.setViewName("user/AddAddr");
		return mv;
	}

	// Addrimpl 과정

	@RequestMapping(value = "/addAddrimpl.top", produces = "text/plain;charset=UTF-8")
	public String addAddrimpl(HttpServletRequest request) {
		ArrayList<String> hq_addr = new ArrayList<String>();
		HttpSession session = request.getSession();
		String hqid = (String) session.getAttribute("loginId");
		String chainname = request.getParameter("chainname").trim();
		String regdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

		// 주소는 여러개 이기때문에 아래와 같은 parsing과정이 필요하다

		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String name = (String) params.nextElement();
			if (name.startsWith("address")) {
				String inputaddr = request.getParameter(name);
				inputaddr = inputaddr.replace(",", " ");
				hq_addr.add(inputaddr);
				System.out.println(inputaddr + "----------- check ADDR--------------");
			}
		}

		// 주소를 입력하는 동시에 chain INSERT 시작된다!
		for (int i = 0; i < hq_addr.size(); i++) {
			System.out.println("check insert");
			ChainVO chain = new ChainVO();
			chain.setChainName(chainname);
			chain.setChainRegDate(regdate);
			chain.setHqID(hqid);
			chain.setChainAddress(hq_addr.get(i));

			try {
				chainbiz.register(chain);
			} catch (Exception e) {
				System.out.println("insert exception!");
				return "redirect:addAddr.top";
			}
			System.out.println("success insert chainvo :" + i + "st");
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main/main");
		return "redirect:main.top";
	}

	@RequestMapping(value = "/read.top", method = RequestMethod.POST)
	public @ResponseBody JSONArray AjaxRead(HttpServletRequest request) {
		HttpSession session = request.getSession();

		String hqid = (String) session.getAttribute("loginId");

		// step1. hq가 맡는 chainid를 모두 불러온다
		ArrayList<String> chainIdList = new ArrayList<String>();
		ArrayList<ChainVO> clist = chainbiz.getbyhq(hqid);

		System.out.println("clist :" + clist);
		for (ChainVO element : clist) {
			String chainid = element.getChainID();
			System.out.println(chainid);
			chainIdList.add(chainid);

		}
		

		// STEP2. 해당하는 CHAINID에 따른 모든 noti를 불러온다
		// 이떄의 notification은 refresh ="true"일때만 불러온다

		ArrayList<NotiVO> notiList = new ArrayList<NotiVO>();

		for (int i = 0; i < chainIdList.size(); i++) {
			notivo = notibiz.get(chainIdList.get(i));
			if (notivo == null) {
				continue;
			}
			System.out.println("refresh : " + notivo.getRefresh());
			if (notivo != null && notivo.getRefresh().equals("new")) {
			
				notiList.add(notivo);
				System.out.println(notivo.getChainid());
				notibiz.refreshStateTrue(notivo.getChainid());
				try {
					notibiz.modify(notivo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	
		// step3. 이제는 자바스크립트로 보낼 애들만 !
		// and ajax로 보냇다면 -> update로 상태를 바꿔준다

		JSONArray array = new JSONArray();
		for (NotiVO element : notiList) {
			JSONObject data = new JSONObject();
			String applycnt = element.getApplycnt();

			System.out.println("applycnt" + applycnt);
			String chainid = element.getChainid();
			System.out.println("chainid" + chainid);
			data.put("chainid", chainid);
			data.put("applycnt", applycnt);
//			notibiz.refreshstate(chainid);
			array.add(data);
		}

		System.out.println("success ajax!");
		return array;

	}

	// get true only
	@RequestMapping(value = "/readTrue.top", method = RequestMethod.POST)
	public @ResponseBody JSONArray AjaxRead2(HttpServletRequest request) {
		HttpSession session = request.getSession();

		String hqid = (String) session.getAttribute("loginId");

		// step1. hq가 맡는 chainid를 모두 불러온다
		ArrayList<String> chainIdList = new ArrayList<String>();
		ArrayList<ChainVO> clist = chainbiz.getbyhq(hqid);

		for (ChainVO element : clist) {
			String chainid = element.getChainID();
			System.out.println(chainid);
			chainIdList.add(chainid);

		}
		System.out.println(chainIdList);

		// STEP2. 해당하는 CHAINID에 따른 모든 noti를 불러온다
		// 이떄의 notification은 refresh ="true"일때만 불러온다

		ArrayList<NotiVO> notiList = new ArrayList<NotiVO>();

		for (int i = 0; i < chainIdList.size(); i++) {
			notivo = notibiz.get(chainIdList.get(i));
			if (notivo == null) {
				continue;
			}
			System.out.println("refresh : " + notivo.getRefresh());
			if (notivo != null && notivo.getRefresh().equals("true")) {
				notiList.add(notivo);
				System.out.println(notivo.getChainid());
				notibiz.refreshStateTrue(notivo.getChainid());
				try {
					notibiz.modify(notivo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		JSONArray array = new JSONArray();
		for (NotiVO element : notiList) {
			JSONObject data = new JSONObject();
			
			String applycnt = element.getApplycnt();
			String chainid = element.getChainid();
			
			data.put("chainid", chainid);
			data.put("applycnt", applycnt);
//			notibiz.refreshstate(chainid);
			array.add(data);
		}

		System.out.println(array);
		System.out.println("success ajax!");
		return array;

	}

	// 7. update page

	@RequestMapping("/update.top")
	public ModelAndView updatemypage(ModelAndView mv, HttpServletRequest request) {

		// step1. 해당 hq에 대한 정보를 가져온다!
		mv = new ModelAndView();
		HttpSession session = request.getSession();
		String hqid = (String) session.getAttribute("loginId");
		System.out.println("enter into updatepage : " + hqid);

		HeadquarterVO hqvo = hqbiz.get(hqid);
		System.out.println(hqvo);

		ArrayList<HeadquarterVO> hqlist = new ArrayList<HeadquarterVO>();
		hqlist.add(hqvo);
		System.out.println(hqlist);

		mv.addObject("center", "../user/update");
		mv.addObject("hqlist", hqlist);

		mv.setViewName("main/main");
		return mv;

	}

	// 8.updateimpl

	@RequestMapping("/updateimpl.top")
	public String updateimpl(HttpServletRequest request) {

		HttpSession session = request.getSession();
		String hqid = (String) session.getAttribute("loginId");
		System.out.println("enter into updatepage : " + hqid);
		String hqName = request.getParameter("cname").trim();
		String hqPwd = request.getParameter("hqpwd").trim();
		String hqEmail = request.getParameter("hqemail").trim();
		String hqPhone = request.getParameter("hqphone").trim();
		String chainCount = request.getParameter("chaincnt").trim();
		String hqAddress = request.getParameter("caddr").trim();

		try {
			HeadquarterVO hqupdate = new HeadquarterVO(hqName, hqPwd, hqEmail, hqPhone, hqAddress, chainCount, hqid);
			hqbiz.modify(hqupdate);
		} catch (Exception e) {
			System.out.println("sql exception!");
		}

		return "redirect:main.top";

	}

}
