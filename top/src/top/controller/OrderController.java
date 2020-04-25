package top.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import top.frame.Biz;
import top.vo.IngredientVO;
import top.vo.OrderDetailVO;
import top.vo.OrderVO;

@Controller
public class OrderController {
	@Resource(name = "ingbiz")
	Biz<String, IngredientVO> biz;

	@Resource(name = "orderbiz")
	Biz<String, OrderVO> biz2;
	
	@Resource(name = "orderdetailbiz")
	Biz<String, OrderDetailVO> biz3;
		
	@RequestMapping("/sendDataToDB.top")
	public void sendDataToDB(HttpServletRequest req) throws Exception{
		System.out.println("sendDataToDB start");
		String json = req.getParameter("data1");
		String loadProds = req.getParameter("loadProds");
		int dataLength = Integer.parseInt(loadProds);
		
		ArrayList<String> orderDetailID = new ArrayList<>();
		
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		
		try {
			jsonObject = (JSONObject) parser.parse(json);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i = 0 ;i < dataLength; i++) {
			System.out.println("update data");
			orderDetailID.add((String) jsonObject.get(Integer.toString(i)));

			/*
			 * 수정 필요!
			 * 
			 * */
			//			biz3.update(orderDetailID.get(i));
		}
	}
	
	@RequestMapping("/insertDataToDB.top")
	public void insertDataToDB(HttpServletRequest req) throws Exception{
		String json = req.getParameter("data1");
		String sortBy = req.getParameter("loadProds");
		int dataLength = Integer.parseInt(sortBy);
		ArrayList<String> orderDetailID = new ArrayList<>();
		
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) parser.parse(json);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i = 0 ;i < dataLength; i++) {
			orderDetailID.add((String) jsonObject.get(Integer.toString(i)));
			/*
			 * 수정 필요!
			 * 
			 * */
//			biz3.update(orderDetailID.get(i));
		}
	}

	@RequestMapping("/orderStatusData.top")
	@ResponseBody
	public void orderStatusData(@RequestParam("chainOrHQ") int chainOrHQ, HttpServletResponse res) throws IOException{
		res.setContentType("text/json;charset=UTF-8");
		PrintWriter out = res.getWriter();
		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();

//		HttpSession session = req.getSession();
//		String u_id = (String) session.getAttribute("loginid");
//		System.out.println(u_id);
		if(chainOrHQ == 0) {
			System.out.println("chainOrHQ 는 0 입니다."); // 체인 아이디 
		}else {
			System.out.println("chainOrHQ 는 1 입니다."); // 헤드쿼터 아이디 
		}
		
		ArrayList<OrderDetailVO> list = null;
		list = biz3.get();//
		
		for(OrderDetailVO u: list) {
			jo = new JSONObject();
			jo.put("orderDetailID" , u.getOrderDetailID());
			jo.put("OrderID", u.getOrderID());
			System.out.println(u.getOrderID());
			jo.put("HqName", u.getHqName() );
			jo.put("ChainName", u.getChainName());
			jo.put("UserName", u.getUserName());
			jo.put("IngName", u.getIngName());
			jo.put("IngPrice", u.getIngPrice());
			jo.put("IngUnit", u.getIngUnit());
			jo.put("IngWeight", u.getIngWeight());
			jo.put("ConID", u.getConID());
			System.out.println(u.getConID());
			jo.put("IngQuantity", u.getIngQuantity());
			jo.put("OrderState", u.getOrderState());
			System.out.println("HqName은  " + u.getHqName());
			ja.add(jo);
		}
		out.print(ja.toJSONString());
		out.close();
	}
	
	@RequestMapping("/orderStatus.top")
	public ModelAndView main(ModelAndView mv, HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("text/json;charset=UTF-8");
		String u_id = "test01";
		mv.addObject("loginId", u_id);
		mv.addObject("AllChainsVisualAnalysis", "../main/orderStatus");
		mv.setViewName("main/main");
		res.setContentType("text/html; charset=UTF-8");
		return mv;
	}
	
	
	
	
	@RequestMapping("/orderData.top")
	@ResponseBody
	public void orderData(HttpServletResponse res) throws IOException {
		System.out.println("orderData Started");
//		HttpSession session = req.getSession();
//		String u_id = (String) session.getAttribute("loginid");
//		System.out.println(u_id);
		res.setContentType("text/json;charset=UTF-8");
		PrintWriter out = res.getWriter();
		ArrayList<IngredientVO> list =null;
		list = biz.get();//
		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();
		///select option의 default값 주기 위해서 아래와 같이 설정
		jo.put("IngName", "선택");
		jo.put("IngPrice", 0);
		jo.put("IngUnit", "kg");
		jo.put("IngImgName","dd");
		ja.add(jo);
		for(IngredientVO u: list) {
			System.out.println(u.getIngName());
			jo = new JSONObject();
			jo.put("IngName", u.getIngName());
			jo.put("IngPrice", u.getIngPrice());
			jo.put("IngUnit", u.getIngUnit());
			jo.put("IngImgName", u.getIngImgName());
			ja.add(jo);
		}
		out.print(ja.toJSONString());
		out.close();
		System.out.println("orderData success");
		return;
	}
	 @RequestMapping("/popupOrder.top")
	 @ResponseBody	 
	 public ModelAndView popupOrder(ModelAndView mv) throws Exception {
			String u_id = "test01";
			mv.addObject("loginId", u_id); 
			//기업 아이디 접속인 경우와 체인 아이디 접속인 경우 두가지 
			mv.setViewName("main/popupOrder");
	        return mv;
	 }

}
