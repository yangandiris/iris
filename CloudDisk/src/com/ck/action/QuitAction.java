package com.ck.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;


@Component
public class QuitAction extends ActionSupport{
	
	@Override
	public String execute() {				
		HttpServletRequest request=ServletActionContext.getRequest();		
		request.getSession().removeAttribute("nickname");
		return SUCCESS;
		
	}

}
