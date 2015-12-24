package com.ck.action;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ck.dao.UserFileDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.ActionSupport;


@Component
public class FiledeleteAction extends ActionSupport{	
	@Autowired
	private UserFileDao userFileDao;
	
	private String deleteitem;

	public void setDeleteitem(String deleteitem) {
		this.deleteitem = deleteitem;
	}



	@Override
	public String execute() throws Exception {
		Gson json=new Gson();
		String[] items=json.fromJson(deleteitem,new TypeToken<String[]>(){}.getType());
		for(int i=0;i<items.length;i++){
			String fileid=items[i].substring(0, items[i].indexOf(":"));
			String parent=items[i].substring(items[i].indexOf(":")+1);
			userFileDao.deletefile(Long.parseLong(fileid),parent);
		}
        
			return null;
		}
	
	
}
