package com.ck.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ck.dao.UserFileDao;
import com.ck.po.UserFile;
import com.opensymphony.xwork2.ActionSupport;


@Component
public class ListallfileAction extends ActionSupport{	
	@Autowired
	private UserFileDao userFileDao;
	
	public List<UserFile> getFile() {
		return file;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	private String pname;
	
	private List<UserFile> file;

	@Override
	public String execute() throws Exception {
		file=userFileDao.QueryByName(pname);
		return SUCCESS;
	}

}
