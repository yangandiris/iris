package com.yang.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.bean.Customer;
import com.yang.service.LoginService;
import com.yang.service.impl.LoginServiceImpl;

@Controller
@Transactional
public class LoginAction {
	@Autowired
	private LoginService loginservice;
	
	
//	@RequestMapping(value="login")
//    public String Login(){
//    	System.out.println(loginservice.Login("yang", "yang"));
//    	return "success";
//    }

	@Autowired
	private  HttpServletRequest request;
		
	@RequestMapping(value="save")
    public String Save(){
    	System.out.println("Saveuser");
    	return "login";
    }
	@RequestMapping(value = "saveuser") 
    @ResponseBody  
    public void saveUser(@RequestBody List<Customer> users) { 
		System.out.println(123);
        System.out.println(users.get(0).getUsername());
		//return users; 
    } 
	@RequestMapping(value = "logger") 
    @ResponseBody  
    @Test
    public void logg() { 
		//LoginService loginservice1=new LoginServiceImpl();
		loginservice.Login("yangmiao", "12314");
    } 
	@RequestMapping(value = "setcode")
	@ResponseBody 
    public String setCode(String yzmcode) { 
		System.out.println(yzmcode);
		request.getSession().setAttribute("code", yzmcode);
		String X=yzmcode;
		return X;
    } 
}
