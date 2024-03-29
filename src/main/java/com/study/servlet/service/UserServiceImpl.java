package com.study.servlet.service;

import com.study.servlet.entity.User;
import com.study.servlet.repository.UserRepository;
import com.study.servlet.repository.UserRepositoryImpl;

public class UserServiceImpl implements UserService{
	
	// Service 객체 싱글톤 정의
	private static UserService instance;
	public static UserService getInstance() {
		if(instance == null) {
			instance = new UserServiceImpl();
		}
		return instance;
	}
	
	// Repository 객체 DI 
	private UserRepository userRepository;
	
	private UserServiceImpl() {
		userRepository = UserRepositoryImpl.getInstance();
	}
	
	
	@Override
	public int addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUser(String username) {
		return userRepository.findUSerByUsername(username);
	}

	@Override
	public boolean duplicatedUsername(String username) {
		User user = userRepository.findUSerByUsername(username);
		
		return user != null;
	}
	
}
