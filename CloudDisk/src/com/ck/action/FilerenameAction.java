package com.ck.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ck.dao.UserFileDao;
import com.ck.po.UserFile;
import com.ck.util.Filesdir;
import com.opensymphony.xwork2.ActionSupport;


@Component
public class FilerenameAction extends ActionSupport{
	@Autowired
	private UserFileDao userFileDao;
	private String path;
	public void setPath(String path) {
		this.path = path;
	}
	private String name;
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	@Override
	public String execute() throws Exception {
		userFileDao.Rename(path,name);
		return SUCCESS;
		
	}
	
	
}
