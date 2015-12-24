package com.ck.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ck.dao.UserDao;
import com.ck.dao.UserFileDao;
import com.ck.po.UserFile;
import com.opensymphony.xwork2.ActionSupport;

@Component
@Scope("prototype")
public class LockfileAction extends ActionSupport {
    @Autowired
	private UserFileDao userFileDao;
	private String fid;
	private int ret;
	public int getRet() {
		return ret;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}



	public String execute() throws Exception  {
		if(userFileDao.lockfile(Long.parseLong(fid))){
			ret=1;
		}else{
			ret=0;
		}
		return SUCCESS;

	}
	
}