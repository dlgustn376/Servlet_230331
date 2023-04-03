package com.study.servlet.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


@WebServlet("/car")
public class CarInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
//  doGet
//  Map<String, String>
//	Map: id, model
//	{id=1, model=쏘나타}
//	{id=2, model=K5}
//	{id=3, model=SM6}
	
//	요청데이터: id
//	String id = request.getParmeter("id");
//	String model = request.getParmeter("model");
	
//	응답 데이터 JSON 양식
//	{"id":"2", "model":"K5"}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		System.out.println("GET 요청");
		
		Gson gson = new Gson();
		Map<String, String> carMap = new HashMap<>();
		carMap.put("1", "쏘나타");
		carMap.put("2", "K5");
		carMap.put("3", "SM6");
		
		String id = request.getParameter("id");
		String findModel = carMap.get(id);
		
		JsonObject responseData = new JsonObject();
		
		if(findModel==null) {
			responseData.addProperty("statusCOde", 400);
			responseData.addProperty("errorMessage", "Not Found!!");
		}else {
			responseData.addProperty("id", id);
			responseData.addProperty("model", findModel);			
		}
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().println(responseData.toString());
		
		}

//  doPost
	
//	{
//		{"id":"1", "model":"쏘나타"},
//		{"id":"2", "model":"K5"},
//		{"id":"3", "model":"SM6"}
//	}
	
//	console
//	id(1): 쏘나타
//	id(2): K5
//	id(3): SM6

//	HTML 응답
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServletInputStream inputStream = request.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

		response.setContentType("aplication/json;Charset=UTF-8");
		System.out.println("POST 요청");
		
		String requestJson = bufferedReader.lines().collect(Collectors.joining());
		
		Gson gson = new Gson();
		List<Map<String, String>> requestMap = gson.fromJson(requestJson, List.class);
		
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		requestMap.forEach(obj -> {
			System.out.println("id("+ obj.get("id") +"): " + obj.get("model"));
			out.println("id("+ obj.get("id") +"): " + obj.get("model"));
		});
	}

}
