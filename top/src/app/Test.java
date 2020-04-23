package app;

import java.util.ArrayList;

<<<<<<< HEAD
=======
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
>>>>>>> 40f9fadf40938334de6bf4230644184efe8f4633
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import top.frame.Biz;
import top.vo.ChainVO;
<<<<<<< HEAD
import top.vo.HeadquarterVO;
import top.vo.NotiVO;
import top.vo.UserVO;
=======
import top.vo.NotiVO;
>>>>>>> 40f9fadf40938334de6bf4230644184efe8f4633

public class Test {

	public static void main(String[] args) {
		AbstractApplicationContext factory = new GenericXmlApplicationContext("myspring.xml");

		// Headquarter
<<<<<<< HEAD
		Biz<String, ChainVO> chainbiz = (Biz) factory.getBean("chainbiz");
		Biz<String, NotiVO> notibiz = (Biz) factory.getBean("notibiz");
		
		Biz<String, HeadquarterVO> hqbiz =(Biz)factory.getBean("hqbiz");
		
		Biz<String, UserVO> ubiz = (Biz) factory.getBean("ubiz");
		System.out.println("test start");
		String hqid = "hennie";
		
		
		// noti check
		
		
		ArrayList<ChainVO> chainvolist = chainbiz.getnotifi(hqid);
		ArrayList<String> chainlist = new ArrayList<String>();
		
		
		for(ChainVO element : chainvolist) {
			chainlist.add(element.getChainID());
		}
		
		ArrayList<NotiVO> arr = notibiz.get();
		ArrayList<NotiVO> notilist = new ArrayList<NotiVO>();
		
		for(int i=0; i<arr.size();i++) {
			for(String element : chainlist) {
				if(arr.get(i).getChainid().contains(element)) {
					notilist.add(arr.get(i));
				}
			}
=======
		
		Biz<String, ChainVO> chainbiz = (Biz) factory.getBean("chainbiz");
		Biz<String, NotiVO> notibiz = (Biz) factory.getBean("notibiz");
		
		System.out.println("test start");
		String hqid = "hennie";
		
	
		
		// step1. hq가 맡는 chainid를 모두 불러온다
		ArrayList<String> chainIdList = new ArrayList<String>();
		ArrayList<ChainVO> clist= chainbiz.getnotifi(hqid);
		for(ChainVO element : clist) {
			String chainid = element.getChainID() ;
			System.out.println(chainid);
			chainIdList.add(chainid);
			
		}
		System.out.println(chainIdList);
		System.out.println("------------------success get chainidList-----------------------");
		System.out.println("------------------start notiList-----------------------");
		
		// STEP2. 해당하는 CHAINID에 따른 모든 noti를 불러온다 
		// 이떄의  notification은 refresh ="true"일때만 불러온다
		System.out.println("chainIdList size : "+chainIdList.size());
		
		ArrayList<NotiVO> notiList = new ArrayList<NotiVO>();
		
		for(int i=0; i<chainIdList.size();i++) {
			NotiVO noti =  notibiz.get(chainIdList.get(i));
			if(noti!=null) {
				System.out.println(noti);
				notiList.add(noti);	
			}
		}
		System.out.println("check notiList size : " + notiList.size());

			
		// step3. 이제는 자바스크립트로 보낼 애들만 !
		// and ajax로 보냇다면 -> update로 상태를 바꿔준다
	
		
		JSONArray array = new JSONArray();
		for(NotiVO element : notiList) {
			JSONObject data = new JSONObject();
			String applycnt = element.getApplycnt();
					
			System.out.println("applycnt" + applycnt);
			String chainid = element.getChainid();
			System.out.println("chainid" + chainid);
			data.put("chainid", chainid);
			data.put("applycnt", applycnt);
			notibiz.refreshstate(chainid);
			array.add(data);
>>>>>>> 40f9fadf40938334de6bf4230644184efe8f4633
		}
		System.out.println(notilist);
		System.out.println("success");
		// update impl
		
<<<<<<< HEAD
//		System.out.println("enter into updatepage : " + hqid);
//		String hqName ="bluebottle-update";
//		String hqPwd ="hennie";
//		String hqEmail = "hennie92@naver.com";
//		String hqPhone = "01029051070";
//		String chainCount = "10";
//		String hqAddress ="강남구 역삼동";
//		System.out.println("check");
//		HeadquarterVO hqupdate = new HeadquarterVO(hqName, hqPwd, hqEmail, hqPhone, hqAddress, chainCount,hqid);
//	
//
//		System.out.println(hqupdate);
//		hqbiz.modify(hqupdate);
//		System.out.println("success!");
		
		
//		//update my page
//		
//		HeadquarterVO hqvo =  hqbiz.get("hennie");
//		System.out.println(hqvo);
//		
		
=======
		System.out.println(array);
		System.out.println("success ajax!");
			

		
		
	
		
		
		
		
		
		

		// Tested. Result : OK

		// Chain

		// Container
>>>>>>> 40f9fadf40938334de6bf4230644184efe8f4633

		
		
//	
//		
//		// step1. hq가 맡는 chainid를 모두 불러온다
//		ArrayList<String> chainIdList = new ArrayList<String>();
//		ArrayList<ChainVO> clist= chainbiz.getnotifi(hqid);
//		for(ChainVO element : clist) {
//			String chainid = element.getChainID() ;
//			System.out.println(chainid);
//			chainIdList.add(chainid);
//			
//		}
//		System.out.println(chainIdList);
//		System.out.println("------------------success get chainidList-----------------------");
//		System.out.println("------------------start notiList-----------------------");
//		
//		// STEP2. 해당하는 CHAINID에 따른 모든 noti를 불러온다 
//		// 이떄의  notification은 refresh ="true"일때만 불러온다
//		System.out.println("chainIdList size : "+chainIdList.size());
//		
//		ArrayList<NotiVO> notiList = new ArrayList<NotiVO>();
//		
//		for(int i=0; i<chainIdList.size();i++) {
//			NotiVO noti =  notibiz.get(chainIdList.get(i));
//			if(noti!=null) {
//				System.out.println(noti);
//				notiList.add(noti);	
//			}
//		}
//		System.out.println("check notiList size : " + notiList.size());
//
//			
//		// step3. 이제는 자바스크립트로 보낼 애들만 !
//		// and ajax로 보냇다면 -> update로 상태를 바꿔준다
//	
//		
//		JSONArray array = new JSONArray();
//		for(NotiVO element : notiList) {
//			JSONObject data = new JSONObject();
//			String applycnt = element.getApplycnt();
//					
//			System.out.println("applycnt" + applycnt);
//			String chainid = element.getChainid();
//			System.out.println("chainid" + chainid);
//			data.put("chainid", chainid);
//			data.put("applycnt", applycnt);
//			notibiz.refreshstate(chainid);
//			array.add(data);
//		}
//		
//		System.out.println(array);
//		System.out.println("success ajax!");
//			
//
//		/*
//		 * 1.ajax test : logincheckup을 case문으로 고치기
//		 */
//		
////		public String AjaxView(@RequestParam("ID") String id, @RequestParam("password") String pwd,
////				@RequestParam("radio") String radio, String str, HttpServletRequest request)
////				throws UnsupportedEncodingException {
////			System.out.println("enter into login check!");
////			request.setCharacterEncoding("UTF-8");
//		
//		// ajax로 부터 받은 parameters
//		String id= "hennie";
//		String radio="hq";
//		String pwd= "hennie";
//		String str="";
//		
//		
//		
//		switch (radio) {
//	
//		case "hq":
//			HeadquarterVO hq = hqbiz.get(id);
//			System.out.println(hq);
//			if (hq.getHqPwd().equals(pwd)) {
//				str = "Yes";
//			} else {
//				str = "NO";
//			}
//			break;
//		case "user":
//			UserVO user = ubiz.get(id);
//			System.out.println(user);
//			if (user.getUserPwd().equals(pwd)) {
//				str = "Yes";
//			} else {
//				str = "NO";
//			}
//			break;
//		default : 
//			str ="NO";
//			break;
//		}
//		System.out.println(str);
//		
//		
		
		
		
//			try {
//				// login 하는 사람이 user일때
//				if (radio.equals("user")) {
//					UserVO user = ubiz.get(id);
//					System.out.println(user);
//					if (user.getUserPwd().equals(pwd)) {
//						str = "Yes";
//					} else {
//						str = "NO";
//
//					}
//					// login 하는 사람이 hq일떄
//				} else if (radio.equals("hq")) {
//					HeadquarterVO hq = hqbiz.get(id);
//					System.out.println(hq);
//					if (hq.getHqPwd().equals(pwd)) {
//						str = "Yes";
//					} else {
//						str = "NO";
//					}
//				}
//
//			} catch (Exception e) {
//				str = "NO";
//				return str.trim();
//			}
//			return str;
//		}

		
		
		
		
		

	}

}
