package app;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import top.frame.Biz;
import top.vo.ChainVO;

public class Test {

	public static void main(String[] args) {
		AbstractApplicationContext factory = new GenericXmlApplicationContext("myspring.xml");

		// Headquarter

		// User

		// Ingredient
		Biz<String, ChainVO> chainbiz = (Biz) factory.getBean("chainbiz");
		System.out.println("test start");
		String hqid = "hennie";
		System.out.println(hqid);
		ArrayList<ChainVO> clist= chainbiz.getnotifi(hqid);
		
		for(ChainVO element : clist) {
			System.out.println(element);
		}
		JSONArray array = new JSONArray();
		for(ChainVO element : clist) {
			JSONObject data = new JSONObject();
			String applycnt = element.getApplycnt();
			String chainid = element.getChainID();				
			data.put("chainid", chainid);
			data.put("applycnt", applycnt);
			array.add(data);
		}
		
		System.out.println(array.toJSONString());
	
		
//		String jsondata = response.toJSONString(response);
		
//		System.out.println(jsondata);
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
