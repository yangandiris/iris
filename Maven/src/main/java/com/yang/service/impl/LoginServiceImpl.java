package com.yang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yang.bean.Customer;
import com.yang.dao.CustomerMapper;
import com.yang.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
	private CustomerMapper customermapper;
	@Override
	public boolean Login(String username, String password) {
		Customer c=new Customer();
		c.setId(10L);
		c.setUsername(username);
		c.setPassword(password);
		customermapper.insert(c);
		return true;
	}

}
