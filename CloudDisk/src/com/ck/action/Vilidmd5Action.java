package com.ck.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ck.dao.UserDao;
import com.ck.dao.UserFileDao;
import com.ck.po.UserFile;
import com.opensymphony.xwork2.ActionSupport;

@Component
public class Vilidmd5Action extends ActionSupport{
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserFileDao userFileDao;
    private String md5;
    private String name;
    private String parent;
    private int ret;
	public int getRet() {
		return ret;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	@Override
	public String execute() throws Exception {
       List<String> list=userFileDao.vilidmd5(md5);
       if(!list.isEmpty()){
    	   if("".equals(name)||null==name){
    		   ret=1;
    	   }else{
	    	    UserFile uf=new UserFile();
				uf.setF_name(name);
				uf.setF_date(getDate());
				uf.setF_parent(parent);
				uf.setIsfolder(0);
				if(parent.indexOf("/")!=-1){
					uf.setC_id(userDao.getidbyname(parent.substring(0, parent.indexOf("/"))));
				}else{
					uf.setC_id(userDao.getidbyname(parent));
				}
				uf.setMd5(md5);
				
				uf.setF_size(list.get(0));
				uf.setF_type(list.get(2));
				uf.setF_url(list.get(1));
				userFileDao.saveUserFile(uf);
			    ret=1;
    	   }
       }else{
    	   ret=0;
       }

		return SUCCESS;
	}
	public static String getDate(){
    	Date d = new Date();
    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	return  format.format(d);
    	
    }
}
