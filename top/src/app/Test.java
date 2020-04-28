package app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import top.frame.Biz;
import top.vo.OrderDetailVO;

public class Test {

	public static void main(String[] args) {
		
		AbstractApplicationContext factory = new GenericXmlApplicationContext("myspring.xml");
		
	
		
	
		Biz<String, OrderDetailVO> odbiz =  (Biz) factory.getBean("orderdetailbiz");
		
		
		String regdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		
		
		ArrayList<OrderDetailVO> list = null;
		list = odbiz.get();//
		
		
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
		
//	
//		// 1.  android 에서 아래와 같이 arr 를 string으로 변환해서 올것이다는 가정하에 쓴 코드
//		JSONObject data1 = new JSONObject();
//		JSONObject data2 = new JSONObject();
//		JSONArray arr = new JSONArray();
//		data1.put("menuID", "menuID_ 1000000");
//		data1.put("menuName", "아메리카노");
//		data1.put("menuCost", 4000);
//		data1.put("menuCount", 5);		
//		data1.put("chainID", "chainID_1000000");
//		
//		
//		data2.put("menuID", "menuID_ 1000001");
//		data2.put("menuName", "icecream");
//		data2.put("menuCost", 30000);
//		data2.put("menuCount", 5);		
//		data2.put("chainID", "chainID_1000000");
//	
//		
//		arr.add(data1);
//		arr.add(data2);
//	
//		
//	
//		// 받은 array spring에서는 str 형태로 받을 것이다
//		String str= arr.toString();	
//		
//////	-------------------여기서 부터 spring code ----------------------////
//
//		
//
////		String menuCount;
//		
//		JSONParser parser = new JSONParser();
//		try {
//			Object obj = parser.parse(str);
//			JSONArray jarr = (JSONArray) obj; 
//			System.out.println(obj);
//			for(int i=0;i<jarr.size();i++) {
//				
//				MenuVO mv = new MenuVO();
//				JSONObject jo = (JSONObject) jarr.get(i);
//				
//				String chainID = (String) jo.get("chainID");
//				String salesID = (salesbiz.getbychain(chainID)).getSalesID();
//				String regdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));	
//				String menuID = (String) jo.get("menuID");
//				String menuName = (String) jo.get("menuName");
//				String menuPrice = (String) jo.get("menuCost");
//				String menuCount = (String) jo.get("menuCount");
//				
//
//				SalesDetailVO sd = new SalesDetailVO();
//				sd.setMenuName(menuName);				
//				sd.setMenuPrice(menuPrice);
//				sd.setSalesDetailRegDate(regdate);
//				sd.setSalesDetailID("salesDetailID");
//				sd.setSalesID(salesID);
//				sd.setMenuName(menuName);
//				sd.setMenuCount(menuCount);
//				System.out.println(sd);
//				
//			}
//			
//
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	
//	}
//	

}}


