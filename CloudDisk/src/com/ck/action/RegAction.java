package com.ck.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ck.dao.UserDao;
import com.ck.po.Customer;
import com.ck.util.Md5;
import com.ck.vo.RegUser;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
@Component
@Scope("prototype")
public class RegAction extends ActionSupport implements ModelDriven<RegUser>{
	private RegUser u=new RegUser();
	@Override
	public RegUser getModel() {
		
		return u;
	}
	@Autowired
	private UserDao userDao;
	
	List<String> msg=new ArrayList<String>();
	Map<String,Object> result = new HashMap<String, Object>();
	String username="";
	//@Override
	public String execute() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String code=(String) request.getSession().getAttribute("yzmcode");
		String action="";
		//验证模块
		if("".equals(u.getEmailnum())&&"".equals(u.getPhonenum())){
			msg.add("手机号或邮箱不能为空");
		}
		if("".equals(u.getEmailnum())&&!"".equals(u.getPhonenum())){
			username=u.getPhonenum();action="phone";
			if(!isMobileNumber(username)){
			msg.add("手机号码格式不正确");
			}
		}
		if("".equals(u.getPhonenum())&&!"".equals(u.getEmailnum())){
			username=u.getEmailnum();action="email";
			if(!isEmail(username)){
				msg.add("邮箱格式不正确");
				}
		}
		if("".equals(u.getPassword1())||"".equals(u.getPassword2())){
			msg.add("密码不能为空");
		}else if(u.getPassword1().length()<8||u.getPassword2().length()<8){
			msg.add("密码长度不能小于8位");
		}
		else if(!u.getPassword1().equals(u.getPassword2())){
			msg.add("两次输入密码不一致");
		}
		if(!u.getCode().equals(code)){
			msg.add("您进行了非法操作，请通过正规途径注册！");
		}
		
		
		Customer user=new Customer();
		user.setUsername(Md5.md5(username));
		user.setPassword(Md5.md5(u.getPassword1()));
		if(msg.isEmpty()){
			if(userDao.querybyname(Md5.md5(username))){
				msg.add("exist"+action);
			}else{
				msg.add("yes");
				userDao.savep(user);
			}
		}
		
		response.setContentType("text/json; charset=utf-8");
		response.setHeader("cache-control", "no-cache"); 		
		PrintWriter out = response.getWriter();	
		Gson gson=new Gson();
		result.put("msg", msg);
		String str=gson.toJson(result);
		out.print(str);	
		out.flush();
		out.close();
		return null;
		
	}

	public  boolean isMobileNumber(String mobiles) {
	    return Pattern
	            .compile("^((13[0-9])|(15[^4,\\D])|(18[^1^4,\\D]))\\d{8}")
	            .matcher(mobiles).matches();
	}

	public  boolean isEmail(String email){     
	     String str="^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
	      return  Pattern.compile(str).matcher(email).matches();     
	    } 
}
