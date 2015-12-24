package com.ck.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ck.dao.UserDao;
import com.ck.util.Md5;
import com.opensymphony.xwork2.ActionSupport;
@Component
public class LoginAction extends ActionSupport {
	private String username;
	private String password;
    private String msg;
	public String getMsg() {
		return msg;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Autowired
	private UserDao userDao;
	

	@Override
	public String execute() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		if(userDao.querybyname(Md5.md5(username))){
			if(userDao.login(Md5.md5(username), Md5.md5(password))){
				request.getSession().setAttribute("username", username);
				msg="yes";
			}else{
				msg="psw";
			}
		}else{
			msg="name";
		}
		
		return SUCCESS;
	}
}
