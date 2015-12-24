package com.ck.action;

import java.io.File;
import java.io.FileInputStream;
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
public class NewfolderAction extends ActionSupport {
    @Autowired
	private UserFileDao userFileDao;
    @Autowired
    private UserDao userDao;
	private String parent;
	
	private String filename;
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public void setParent(String parent) {
		this.parent = parent;
	}
	

	public String execute() throws Exception {
			UserFile uf=new UserFile();
			uf.setF_name(filename);
			uf.setF_date(formatDate(new Date()));
			uf.setF_size("--");
			uf.setF_type("folder");
			uf.setF_parent(parent);
			uf.setIsfolder(1);
			if(parent.indexOf("/")!=-1){
				uf.setC_id(userDao.getidbyname(parent.substring(0, parent.indexOf("/"))));
			}else{
				uf.setC_id(userDao.getidbyname(parent));
			}
			
			userFileDao.saveUserFile(uf);
		
		return null;

	}
	 public static String formatDate(Date date){
	    	
	    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	return  format.format(date);
	    	
	    }
	 
}