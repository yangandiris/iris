package com.ck.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
@SuppressWarnings("serial")
@Component
public class CodeAction extends ActionSupport{
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		String code=request.getParameter("yzmcode");
		request.getSession().setAttribute("yzmcode", code);
		
		return null;
	}
}
