package com.ck.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ck.dao.UserFileDao;
import com.ck.po.UserFile;
import com.ck.util.Filesdir;
import com.opensymphony.xwork2.ActionSupport;


@Component
@Scope("prototype")
public class GetfilesnodesAction extends ActionSupport{	
	@Autowired
	private  UserFileDao userFileDao=new UserFileDao();
	private String path;
	
	public void setPath(String path) {
		this.path = path;
	}
	
	private List<List> node;

	public List<List> getNode() {
		
		return node;
	}


	@Override
	public String execute() throws Exception {
		node=userFileDao.getnodes(path,"1");
		return SUCCESS;
		
	}
	
	

}
