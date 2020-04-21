package app;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import top.frame.Biz;
import top.vo.ChainVO;
import top.vo.NotiVO;

public class Test {

	public static void main(String[] args) {
		AbstractApplicationContext factory = new GenericXmlApplicationContext("myspring.xml");

		// Headquarter
		
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
		}
		
		System.out.println(array);
		System.out.println("success ajax!");
			

		
		
	
		
		
		
		
		
		

		// Tested. Result : OK

		// Chain

		// Container

		// Order

		// Sales

		// Menu
//		Biz<String, MenuVO> menubiz = (Biz) factory.getBean("menubiz");
//		for (MenuVO i : menubiz.get()) {
//			System.out.println(i);
//		}
		// Tested. Result : OK
	}

}
