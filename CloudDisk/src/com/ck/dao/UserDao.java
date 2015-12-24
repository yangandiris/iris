package com.ck.dao;


import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.ck.po.Customer;
import com.ck.util.Md5;


@Component
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void savep(Customer user) {
		Session s = sessionFactory.getCurrentSession();

		s.save(user);
	}

	@Transactional
	public boolean querybyname(String username) {
		Session s = sessionFactory.getCurrentSession();
		String hql = "select username from  Customer where username='" + username + "'";
		List query = s.createQuery(hql).list();
		if(query.size()==1){
			return true;
		}else{
			return false;
		}
	}
	
	@Transactional
	public long getidbyname(String username) {
		Session s = sessionFactory.getCurrentSession();
		String hql = "select id from  Customer where username='" + Md5.md5(username) + "'";
		List query = s.createQuery(hql).list();
		
		return (long) query.get(0);
		
	}
	@Transactional
	public boolean login(String username,String password) {
		
		Session s = sessionFactory.getCurrentSession();
		String hql = "select password from  Customer where username='" + username + "'";
		boolean islogin=false;
		Iterator query = s.createQuery(hql).list().iterator();
		 while (query.hasNext()) {
		        String  p=(String) query.next();
				if(p.equals(password)){
					islogin=true;
				}else{
					islogin=false;
				}
			}
		 return islogin;
	}
   



}
