package com.yang.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yang.bean.Customer;
import com.yang.controller.LoginAction;
import com.yang.dao.CustomerMapper;
import com.yang.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
	private CustomerMapper customermapper;
    private Logger log=LoggerFactory.getLogger(LoginServiceImpl.class);
	@Override
	public boolean Login(String username, String password) {
		log.error("老子草泥马");
		Customer c=new Customer();
		c.setId(21L);
		c.setUsername(username);
		c.setPassword(password);
		customermapper.insert(c);
		return true;
	}

}
