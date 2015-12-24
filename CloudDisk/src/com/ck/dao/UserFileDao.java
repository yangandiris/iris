package com.ck.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ck.po.UserFile;


@Component
@Scope("prototype")
public class UserFileDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void saveUserFile(UserFile uf) {
		Session s = sessionFactory.getCurrentSession();
		s.save(uf);
	}
	@Transactional
	public List<UserFile> QueryByName(String pname) {
		Session s = sessionFactory.getCurrentSession();
		List<UserFile> flist=new ArrayList<UserFile>();
	    String hql = "from  UserFile where f_parent='" + pname + "' and islock!=1";
		Iterator query = s.createQuery(hql).list().iterator();					
	    while (query.hasNext()) {
	        	
	        UserFile f=(UserFile) query.next();
			flist.add(f);
		}				
	    return flist;
	}
	@Transactional
	public List<UserFile> getpartfile(String path, String action) {
		Session s = sessionFactory.getCurrentSession();
		List<UserFile> flist=new ArrayList<UserFile>();
		String hql=null;
		if(action.equals("text")){
			hql = "from  UserFile where f_type like '%ms%' or f_name like '%.doc' or f_name like '%.ppt' or f_name like '%.xlsx' or f_name like '%.docx' or f_type like 'text%' or f_type like '%pdf' and islock!=1";
		}else if(action.equals("video")){
			hql="from UserFile where  f_name like '%.rm'  or f_name like '%.avi' or f_name like '%.rmvb' or f_name like '%.mp4' and islock!=1";
		}else if(action.equals("image")){
			hql="from UserFile where f_type like 'image%' and islock!=1";
		}else{
			hql = "from  UserFile where islock=1";
		}
	     
		Iterator query = s.createQuery(hql).list().iterator();					
	    while (query.hasNext()) {
	        	
	        UserFile f=(UserFile) query.next();
			flist.add(f);
		}				
	    return flist;
	}
	@Transactional
	public boolean Rename(String path, String name) {
		Session s = sessionFactory.getCurrentSession();
		String hql = "update UserFile  set f_name='"+name+"' where id='"+path+"'";
		int num=s.createQuery(hql).executeUpdate();
		if(num>0){
			return true;
		}else{
			return false;
		}
		
		
		
	}
	@Transactional
	public void deletefile(long fileid,String parent) {
		Session s = sessionFactory.getCurrentSession();
		String hql = "delete  UserFile   where id='"+fileid+"' or f_parent like '"+parent+"%'";
		s.createQuery(hql).executeUpdate();
	}
	 List<List> nodes = new ArrayList<List>();
	@Transactional
	public List<List> getnodes(String path,String pId ) {
		Session s = sessionFactory.getCurrentSession();
		String hql = "select f_name from UserFile  where isfolder=1 and f_parent = :path";
		Iterator query = s.createQuery(hql).setString("path", path).list().iterator();	
		List<String> children=new ArrayList<String>();
	    while (query.hasNext()) {	        	
	    	String o = (String) query.next();
	    	children.add(o);		
		}	
	    for (int i = 0; i < children.size(); i++) {
			
			List l=new ArrayList();
			if(pId.equals("0")){
				l.add(Long.parseLong(String.valueOf(i+1)));
				l.add(Long.parseLong(pId));
				l.add(children.get(i));
				nodes.add(l);
				getnodes(path+"/"+children.get(i),String.valueOf(i+1));
			}else{
				l.add(Long.parseLong(pId+String.valueOf(i+1)));
				l.add(Long.parseLong(pId));
				l.add(children.get(i));
				nodes.add(l);
				getnodes(path +"/"+children.get(i),pId+String.valueOf(i+1));
			}
			
		
	}
	    return nodes;
		
	}
	@Transactional
	public boolean removefile(String path,String parent, long fileid) {
		Session s=sessionFactory.getCurrentSession();
		
		String hqlchild = "select id,f_name from UserFile where f_parent='"+parent+"'";
		Iterator query = s.createQuery(hqlchild).list().iterator();	
	    while (query.hasNext()) {	        	
	    	Object[] o = (Object[]) query.next();
	    	long childid=Long.parseLong(o[0].toString());
	    	String childpath=path+"/"+parent.substring(parent.lastIndexOf("/")+1);
	    	String childparent=parent+"/"+o[1].toString();
	    	removefile(childpath,childparent,childid);
		}	
		String hql = "update UserFile  set f_parent='"+path+"' where id='"+fileid+"'";
		int num=s.createQuery(hql).executeUpdate();
		
		if(num>0){
			return true;
		}else{
			return false;
		}
	}

	 @Transactional
		public List vilidmd5(String md5) {
			Session s = sessionFactory.getCurrentSession();
			String hql = "select f_size,f_url,f_type from  UserFile where md5='" + md5 + "'";
			List list=new ArrayList();
			Iterator query = s.createQuery(hql).list().iterator();
			 while (query.hasNext()) {
				 list.clear();
				 Object[] o = (Object[]) query.next();
				 list.add(o[0].toString());
				 list.add(o[1].toString());
				 list.add(o[2].toString());
				}
			 
			return list;
		}
	 @Transactional
	public boolean lockfile(long fid) {
		int islock = 0,lock=0;
		Session s = sessionFactory.getCurrentSession();
		
		String hql = "select islock from  UserFile where id=" +fid;
		
		Iterator query = s.createQuery(hql).list().iterator();
		 while ( query.hasNext()) {
			 islock=(int) query.next();
		}
		 if(islock==0){
			 lock=1;
		 }
		
		String hqla = "update UserFile  set islock="+lock+" where id="+fid;
		int num=s.createQuery(hqla).executeUpdate();
		if(num>0){
			return true;
		}else{
			return false;
		}
	}
	


}
