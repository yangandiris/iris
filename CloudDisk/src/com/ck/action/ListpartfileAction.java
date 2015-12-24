package com.ck.action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ck.dao.UserFileDao;
import com.ck.po.UserFile;
import com.ck.util.Filesdir;
import com.opensymphony.xwork2.ActionSupport;


@Component
public class ListpartfileAction extends ActionSupport{	
	@Autowired
	private UserFileDao userFileDao;
	private String path;
	public void setPath(String path) {
		this.path = path;
	}
	private String action;
	
	public void setAction(String action) {
		this.action = action;
	}

	public List<UserFile> getFile() {
		return file;
	}

	private List<UserFile> file;
	
	@Override
	public String execute() throws Exception {
		file=userFileDao.getpartfile(path,action);
		
		return SUCCESS;
	}
	
	
}
