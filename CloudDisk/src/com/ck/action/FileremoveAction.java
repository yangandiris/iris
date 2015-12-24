package com.ck.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ck.dao.UserFileDao;
import com.ck.util.Filesdir;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.ActionSupport;


@Component
public class FileremoveAction extends ActionSupport{
	@Autowired
	private UserFileDao userFileDao;
	private String removeitem;
	private String path;
	private boolean success;
	

	public boolean isSuccess() {
		return success;
	}

	@Override
	public String execute() throws Exception {
	
       		Gson json=new Gson();
			String[] item=json.fromJson(removeitem,new TypeToken<String[]>(){}.getType());
			for(int i=0;i<item.length;i++){
				String fileid=item[i].substring(0, item[i].indexOf(":"));
				String parent=item[i].substring(item[i].indexOf(":")+1);	    
				success=userFileDao.removefile(path,parent,Long.parseLong(fileid));
		 	
        }
			return SUCCESS;
		
}

	public void setPath(String path) {
		this.path = path;
	}

	public void setRemoveitem(String removeitem) {
		this.removeitem = removeitem;
	}
	
}
