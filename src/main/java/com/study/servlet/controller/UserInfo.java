package com.study.servlet.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import javax.imageio.stream.ImageInputStreamImpl;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/user")
public class UserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		Gson gson = new Gson();

		System.out.println("GET 요청");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		
		System.out.println(name);
		Map<String,String> userMap = new HashMap<>();
		userMap.put("name", name);
		userMap.put("phone", phone);
		
		String userJson = gson.toJson(userMap);
		System.out.println(userJson);
		/**
		 * 1. 주소창, src, href, replace 등으로 요청할 수 있음.
		 * 2. 데이터를 전달하는 방법(Query String). 
		 * 		http(s)://서버주소:포트/요청메시지?key=value&key=value
		 * 
		 */
		
		
		System.out.println(response.getCharacterEncoding());
		
//		response.addHeader("test", "text/html;charset=UTF-8");
//		response.setContentType("text/html;charset=UTF-8");      //HTML 응답 
		response.setContentType("application/json;Charset=UTF-8");
		
		
		PrintWriter out = response.getWriter();
		out.println("이름: " + name);
		out.println("연락처: " + phone);
		
		
		out.println(userJson);
		}
//  post요청 받을수있는녀석
//  form 태그에 메소드로 바꾸면 사용가능
//  비동기 통신을 (자바스크립트, 리액트) 코드안에서 통신을 요청 할 수 있다.
//  테스트툴을 사용 (포스트맨)

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST 요청");
		request.setCharacterEncoding("UTF-8");
//		System.out.println(request.getParameter("address"));
		//파라미터로 못보냄.
		Gson gson = new Gson();
		
		ServletInputStream inputStream = request.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//		1.
		String json = "";
		String line = null;
		while((line = bufferedReader.readLine()) != null) {
			json += line;
		}
		json.replaceAll(" ","");
		System.out.println(json);
		
//		2. 
//		servlet입장.
//		AtomicReference<String> joinAtomic = new AtomicReference<>("");
//		bufferedReader.lines().forEach(line -> {
//			joinAtomic.getAndUpdate(t -> t + line);
//		});		
//		
//		String json = joinAtomic.get().replaceAll(" ", "");
//		System.out.println(json);

		
// 		3. ChatGPT 작성
//		String json = bufferedReader.lines().collect(Collectors.joining());;
//		System.out.println(json);

		Map<String, String> jsonMap = gson.fromJson(json, Map.class);
		System.out.println(jsonMap);
		
		
		/*
		 * 1. 
		 * <form action = "http://localhost:8080/Servlet_20230331/user" method="post">
		 * 		<input name="key" value="value">
		 * 		<bytton type="submit">요청</button> 
		 * </form>
		 * 
		 * */
	}

}
