package top.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import top.frame.Biz;
import top.vo.IngredientVO;

@Controller
public class IngredientController {

	@Resource(name = "ingbiz")
	Biz<String, IngredientVO> biz;
	SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");

	Date time = new Date();

	@RequestMapping("/List.top")
	public ModelAndView show(ModelAndView mv, HttpServletResponse res) {
		mv.setViewName("inventory/inventory");

		for (IngredientVO i : biz.get()) {
			System.out.println(i);
		}

		mv.addObject("ingredientListData", biz.get());
		// mv.addObject("ingredientListTable", "../ingredient/ingredientList");
		res.setContentType("text/html; charset=UTF-8");

		return mv;
	}

	// Show ingredient list
	@RequestMapping("/ingredientList.top")
	public ModelAndView showIngredientList(ModelAndView mv, HttpServletResponse res, HttpServletRequest request) {
		HttpSession session = request.getSession();

		String hqID = (String) session.getAttribute("loginId");

		System.out.println(hqID);

		for (IngredientVO i : biz.get()) {
			System.out.println(i);
		}

		mv.addObject("loginId", hqID);
		mv.addObject("ingredientListData", biz.get());
		mv.addObject("center", "../ingredient/ingredientList");
		mv.setViewName("main/main");
		res.setContentType("text/html; charset=UTF-8");

		return mv;
	}

	@RequestMapping("/getList.top")
	public ModelAndView getlsit(ModelAndView mv, HttpServletResponse res, HttpServletRequest request) {
		System.out.println("HI");
		HttpSession session = request.getSession();

		String hqID = (String) session.getAttribute("loginId");

		System.out.println(hqID);

		for (IngredientVO i : biz.getUserSpecific(hqID)) {
			System.out.println(i);
		}

		mv.addObject("loginId", hqID);
		mv.addObject("ingredientListData", biz.get());
		mv.addObject("center", "../ingredient/hyunchu_list");
		mv.setViewName("main/main");

		res.setContentType("text/html; charset=UTF-8");

		return mv;
	}

	@RequestMapping(value = "/up.top", produces = "text/plain;charset=UTF-8")
	public String updateimpl(HttpServletRequest request, HttpServletResponse res) {
		System.out.println("UP.ToP");
		HttpSession session = request.getSession();
		String hqID = (String) session.getAttribute("loginId");
		System.out.println(hqID + "1");
		String ingID = request.getParameter("ingID");
		System.out.println(ingID + "2");
		String ingCategory = request.getParameter("ingCategory");
		System.out.println(ingCategory + "3");
		String ingName = request.getParameter("ingName");
		System.out.println(ingName);
		double ingPrice = Double.parseDouble(request.getParameter("ingPrice"));
		System.out.println(ingPrice + "4");
		String ingUnit = request.getParameter("ingUnit");
		System.out.println(ingUnit + "5");
		String ingBrand = request.getParameter("ingBrand");
		System.out.println(ingBrand + "6");
		String ingType = request.getParameter("ingType");
		System.out.println(ingType + "7");
		double ingWeight = Double.parseDouble(request.getParameter("ingWeight"));
		System.out.println(ingWeight + "8");
		String ingRegDate = format1.format(time);
		System.out.println(ingRegDate + 9);
		String ingLink = request.getParameter("ingLink");
		System.out.println(ingLink + "10");
		int ingLinkCount = Integer.parseInt(request.getParameter("ingLinkCount"));
		System.out.println(ingLinkCount + "11");
		String ingImgName = request.getParameter("ingImgName");
		System.out.println(ingImgName);

		try {

			IngredientVO ing = new IngredientVO(ingID, ingCategory, ingName, ingPrice, ingUnit, ingBrand, ingType,
					ingWeight, ingRegDate, ingLink, ingLinkCount, ingImgName, hqID);
			biz.modify2(ing);

			System.out.println("update" + ing);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("????????????");

		}

		res.setContentType("text/html; charset=UTF-8");

		return "redirect:ingredientList.top";

	}

	// udate ingredient

	// Add ingredient

	// Modify ingredient

	// delete ingredient

	@RequestMapping("/indel.top")
	public ModelAndView dlelete(ModelAndView mv, String ingID, HttpServletRequest request) {

		HttpSession session = request.getSession();
		try {
			biz.remove(ingID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (session != null) {
			session.invalidate();
		}
		mv.setViewName("inventory_list");
		return mv;

	}

	@RequestMapping(value = "/insert.top", produces = "text/plain;charset=UTF-8")
	public String insert(HttpServletRequest request, IngredientVO ing, HttpServletResponse res) {
		String str = "";
		HttpSession session = request.getSession();

		String hqID = (String) session.getAttribute("loginId");
		String ingRegDate = format1.format(time);
//				String hqID ="cafe_TOP_hq";
		String ingID = request.getParameter("ingID");
		String ingCategory = request.getParameter("ingCategory");
		String ingName = request.getParameter("ingName");
		double ingPrice = Double.parseDouble(request.getParameter("ingPrice"));
		String ingUnit = request.getParameter("ingUnit");
		String ingBrand = request.getParameter("ingBrand");
		String ingType = request.getParameter("ingType");
		double ingWeight = Double.parseDouble(request.getParameter("ingWeight"));

		String ingLink = request.getParameter("ingLink");
		int ingLinkCount = 0;
		String ingImgName = request.getParameter("ingImgName");
		System.out.println(hqID);
		System.out.println(ingID);
		System.out.println(ingCategory);
		System.out.println(ingName);
		System.out.println(ingPrice);
		System.out.println(ingUnit);
		System.out.println(ingBrand);
		System.out.println(ingType);
		System.out.println(ingWeight);
		System.out.println(ingRegDate);
		System.out.println(ingLink);
		System.out.println(ingLinkCount);
		System.out.println(ingImgName);

		if (ingID != null) {
			try {
				ing = new IngredientVO(ingID, ingCategory, ingName, ingPrice, ingUnit, ingBrand, ingType, ingWeight,
						ingRegDate, ingLink, ingLinkCount, ingImgName, hqID);
				biz.register(ing);

				System.out.println(ing);
			} catch (Exception e) {

				e.printStackTrace();
				System.out.println("�씠嫄� �븞�굹�삤硫� �븞�맖+insert");
				str = "no";

				return "redirect:ingredientList.top";
			}
		} else if (ingID == null) {

		}

		res.setContentType("text/html; charset=UTF-8");

		return "redirect:ingredientList.top?str=" + "str";
	}

	@RequestMapping(value = "/ingdel.top", produces = "text/plain;charset=UTF-8")
	public String delete(ModelAndView mv, HttpServletRequest request) throws UnsupportedEncodingException {
		request.getSession();

		String ingID = java.net.URLDecoder.decode(request.getParameter("ingID"), "UTF-8");

		System.out.println(ingID + "delete");
		try {

			biz.remove(ingID);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return "redirect:ingredientList.top";

	}

	@RequestMapping(value = "/adjust.top")
	@ResponseBody
	public IngredientVO AjaxView(@RequestParam("ingID") String ingID, HttpServletRequest request,
			HttpServletResponse res) throws UnsupportedEncodingException {

		System.out.println("AjaxView" + ingID);
		IngredientVO ingList = biz.get(ingID);
		System.out.println(ingList);
		res.setContentType("text/html;charset=utf-8");
		return ingList;
	}

	@RequestMapping("/iotclient.top")
	public void sendNotiFromIoTClient(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("IOT");
		String ID = req.getParameter("id");
		System.out.println(ID);

		// send notification to manageApp regardless of the said conditions above
		URL url = null;
		try {
			url = new URL("https://fcm.googleapis.com/fcm/send");
		} catch (MalformedURLException e) {
			System.out.println("Error while creating Firebase URL | MalformedURLException");
			e.printStackTrace();
		}
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			System.out.println("Error while createing connection with Firebase URL | IOException");
			e.printStackTrace();
		}
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "application/json");
		// set my firebase server key
		conn.setRequestProperty("Authorization", "key="
				+ "AAAAoMmzl84:APA91bHIGDTXVb45_HKSfAFsr-IGf1K_gvHgQ4p9AMNUD77S5Pv7O4jGXSo-4XQ1aTS4ZDc1db8_8S0VHZsUAJfUwLXCINoPykcoKUJ3CVgUp4JdsD0KnigqqVWzm1ZfgZChI6cTCK1Q");
		// create notification message into JSON format
		JSONObject message = new JSONObject();
//			message.put("to",
//					"cmm9ME4d9Ss:APA91bGxP8xrtRCzEof13dArAAuJKGODYi7uejryVTxkdndEoUxC0NTw2LbNNhUizHS38syfGTmHRBRUzCXj5HLgkQcb2XYeE4eiyGG-kKHSU-OPbSet2AMU_yjv0gQMg0RDLhNy920d");
		message.put("to", "/topics/chainTablet");
		message.put("priority", "high");
		JSONObject notification = new JSONObject();
		notification.put("title", "truck1");
		notification.put("body", ID);
		message.put("notification", notification);
		JSONArray conListArray = new JSONArray();

		// send data to firebase (http method)
		try {
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			out.write(message.toString());
			out.flush();
			conn.getInputStream();
		} catch (IOException e) {
			System.out.println("Error while writing outputstream to firebase sending to ManageApp | IOException");
			e.printStackTrace();
		}
	}

}
