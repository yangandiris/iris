package com.yang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yang.service.LoginService;

@Controller
@Transactional
public class LoginAction {
	@Autowired
	private LoginService loginservice;
	@RequestMapping(value="login")
    public String Login(){
    	System.out.println(loginservice.Login("yang", "yang"));
    	return "index.jsp";
    }
}
