package com.study.servlet.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.study.servlet.entity.User;

/*
 * get 요청
 * 요청 데이터 퀴리파람
 * deGet()
 * UserList에서 username을 통해 사용자 정보 찾기
 * 응답데이터 Json(User)
 */
/*
 * post 요청
 * 요청 데이터 Json(User)
 * dePost()
 * Json을 User 객체로 변환 후 ToString으로 출력
 * 응답데이터 Json{"success": "true"}
 */

@WebServlet("/auth")
public class Authentication extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		System.out.println("GET 요청");
		
		
//		User user = new User();
		String username = request.getParameter("username");
		
		List<User> userList = new ArrayList<>();
		userList.add(new User("aaa", "1234", "a", "aaa@gmail.com"));
		userList.add(new User("bbb", "2341", "b", "bbb@gmail.com"));
		userList.add(new User("ccc", "3412", "c", "ccc@gmail.com"));
		userList.add(new User("ddd", "4123", "d", "ddd@gmail.com"));
		
		User findUser = null;
		
		for (User user : userList) {
			if(user.getUsername().equals(username)) {
				findUser = user;
				break;
			}
		}
		Gson gson = new Gson();

		String userJson = gson.toJson(findUser);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().println(userJson);	
		System.out.println(userJson);

//		String password = request.getParameter("password");
//		String name = request.getParameter("name");
//		String email = request.getParameter("email");
//		
//		Map<String,String> userMap = new HashMap<>();
//		userMap.put("username", username);
//		userMap.put("password", password);
//		userMap.put("name", name);
//		userMap.put("email", email);
//		
//		System.out.println(username);
//		String fineUsername = userMap.get("username");
//		String finePassword = userMap.get("password");
//		String fineName = userMap.get("name");
//		String fineEmail = userMap.get("email");
//		
//		JsonObject responseData = new JsonObject();
//		
//		responseData.addProperty("username", fineUsername);
//		responseData.addProperty("password", finePassword);
//		responseData.addProperty("name", fineName);
//		responseData.addProperty("email", fineEmail);
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("aplication/json;Charset=UTF-8");
		Gson gson = new Gson();
		
		ServletInputStream inputStream = request.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		
		String requestBody = bufferedReader.lines().collect(Collectors.joining());
		
		User user = gson.fromJson(requestBody, User.class);
		
		System.out.println(user);
		
		response.getWriter().println(requestBody);
//		String requestJson = bufferedReader.lines().collect(Collectors.joining());
//		
//		List<Map<String, String>> requestMap = gson.fromJson(requestJson, List.class);
//		
//		PrintWriter out = response.getWriter();
//		
//		requestMap.forEach(obj -> {
//			System.out.println("username: "+ obj.get("username") + " password: " + obj.get("password") + " name: " + obj.get("name") + " email: " + obj.get("email"));
//			out.println("username: "+ obj.get("username") + " password: " + obj.get("password") + " name: " + obj.get("name") + " email: " + obj.get("email"));
//		});
		
	}

}
