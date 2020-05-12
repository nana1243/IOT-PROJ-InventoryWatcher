package top.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import top.frame.Biz;
import top.vo.MenuVO;
import top.vo.SalesDetailVO;
import top.vo.SalesVO;

@Controller
public class POSController {

	@Resource(name = "salesbiz")
	Biz<String, SalesVO> salesbiz;

	@Resource(name = "salesdetailbiz")
	Biz<String, SalesDetailVO> salesdetailbiz;

	SalesDetailVO sd;
	SalesVO sales;

	String regdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

	
	
	@RequestMapping(value = "/pos.top", method = RequestMethod.POST)
	@ResponseBody
	public String getData(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jo) {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		System.out.println("getData From Sales. Android. ---Start");

		System.out.println(jo);

		Map sjo = (Map) (jo.get("jsonData"));

		String saleDate = (String) sjo.get("saleDate");
		int saleCount = (int) sjo.get("saleCount");
		int saleCost = (int) sjo.get("saleCost");
		String saleAdmin = (String) sjo.get("saleAdmin");

		System.out.println("saleData: " + saleDate + " saleCount: " + saleCount + " saleCost :" + saleCost
				+ " saleAdmin :" + saleAdmin);

		String temp = request.getParameter("jsonData");

		ArrayList<SalesVO> slist = new ArrayList<SalesVO>();

		String result = "success";

		return result;
	}

	@RequestMapping(value = "/posorder.top", method = RequestMethod.POST)
	@ResponseBody
	public String orderData(HttpServletRequest request, HttpServletResponse response, @RequestBody String str) {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		
	      JSONParser parser = new JSONParser();
	      try {
	         Object obj = parser.parse(str);
	         JSONObject sjo = (JSONObject) obj;
	        String orderDate = (String) sjo.get("orderDate");
	 		int orderCount = (int) sjo.get("orderCount");
	 		int orderCost = (int) sjo.get("orderCost");
	 		String orderNo = (String) sjo.get("orderNO");
	 		String chainID = (String) sjo.get("chainID");
			sales = new SalesVO();
			sales = salesbiz.getbychain(chainID);
			sales.setSalesID(orderNo);
			sales.setChainID(chainID);
			sales.setTotSales(orderCost);
			sales.setSalesRegDate(regdate);
			insertSaleData(sales);	       

	      } catch (ParseException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
		
	
		String result = "success";

		return result;
	}
	
	
	// json array 로 옮

	@RequestMapping(value = "/posorderdetail.top", method = RequestMethod.POST)
	@ResponseBody
	public String orderDetailData(HttpServletRequest request, HttpServletResponse response,
			@RequestBody String str) {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		System.out.println("getOrderDetailData from Android. ---Start");

		ArrayList<String> temp = new ArrayList<>();
		
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(str);
			JSONArray jsonObj = (JSONArray) obj; 
			
			   for(int i=0;i<jsonObj.size();i++) {
		            
		            MenuVO mv = new MenuVO();
		            JSONObject jo = (JSONObject) jsonObj.get(i);
		            
		            String chainID = (String) jo.get("chainID");
		            String salesID = (salesbiz.getbychain(chainID)).getSalesID();
		            String regdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));   
		            String menuID = (String) jo.get("menuID");
		            String menuName = (String) jo.get("menuName");
		            String menuPrice = (String) jo.get("menuCost");
		            String menuCount = (String) jo.get("menuCount");
		            

		            SalesDetailVO sd = new SalesDetailVO();
		            sd.setMenuName(menuName);            
		            sd.setMenuPrice(menuPrice);
		            sd.setSalesDetailRegDate(regdate);
		            sd.setSalesDetailID("salesDetailID");
		            sd.setSalesID(salesID);
		            sd.setMenuName(menuName);
		            sd.setMenuCount(menuCount);
		            System.out.println(sd);
		            
		         }
		         
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		String result = "success";

		return result;
	}
	
	
	@RequestMapping(value = "/posgetdata.top", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray getSalesData(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		System.out.println("getRequest From Android : get All Sales DATA  ---Start");
		
		ArrayList<SalesVO> salesList = new ArrayList<SalesVO>();
		
		
		try {
			salesList = salesbiz.get();
			for(SalesVO i : salesList) {
				System.out.println(i);
			}
			System.out.println("sales selectAll Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		JSONArray result = new JSONArray();

		return result;
	}
	

	
	public void insertSaleData(SalesVO sales) {

		try {
			salesbiz.register(sales);
			System.out.println("salesData Inserted");
		} catch (Exception e) {
			System.out.println("salesData Inserting Failed");
			e.printStackTrace();
		}
	}

	
	public void insertSalesDetailData(SalesDetailVO sd) {
		try {
			salesdetailbiz.register(sd);
			System.out.println("salesDetailData Inserted");
		} catch (Exception e) {
			System.out.println("salesDetaildata Inserting Failed");
			e.printStackTrace();
		}

	}


}
