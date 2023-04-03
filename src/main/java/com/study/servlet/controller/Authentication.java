package com.study.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.study.servlet.dto.RequestDto;
import com.study.servlet.dto.ResponseDto;
import com.study.servlet.entity.User;
import com.study.servlet.service.UserService;
import com.study.servlet.service.UserServiceImpl;

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
	private Gson gson;
	private UserService userService;
	
	public Authentication() {
		userService = UserServiceImpl.getInstance();
		gson = new Gson();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = RequestDto.<User> convertRequestBody(request, User.class);

		boolean duplicatedFlag = userService.duplicatedUsername(user.getUsername());
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(duplicatedFlag) {			
			// true == 중복, false == 가입가능
			ResponseDto<Boolean> responseDto = 
					new ResponseDto<Boolean>(400, "duplicated username", duplicatedFlag);
			out.println(gson.toJson(responseDto));
			return;
		}
		ResponseDto<Integer> responseDto = 
				new ResponseDto<Integer>(201,"singip", userService.addUser(user));
		out.println(gson.toJson(responseDto));
	}

}
