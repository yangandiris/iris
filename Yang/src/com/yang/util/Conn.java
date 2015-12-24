package com.yang.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Conn {
	SqlSessionFactory sqlsession=null;
	public Conn() {
		String res="xml/mybatis-config.xml";
	    InputStream in=null;
		try {
			in = Resources.getResourceAsStream(res);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    sqlsession=new SqlSessionFactoryBuilder().build(in);
	}
	public  SqlSession getSession(){
		SqlSession session=sqlsession.openSession();
		return session;
		
	}
   
   
    
}
