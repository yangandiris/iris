import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.yang.bean.Customer;
import com.yang.bean.UserEntity;
import com.yang.util.Conn;

public class Conntest {

	public static void main(String[] args) throws IOException {
		Conn conn=new Conn();
		//String res="xml/mybatis-config.xml";
	    //InputStream in=Resources.getResourceAsStream(res);
		//SqlSessionFactory sqlsessionf=new SqlSessionFactoryBuilder().build(in);
		SqlSession session=conn.getSession();
		List<UserEntity> list=session.selectList("UserEntityMapper.selectall");
		//UserEntity u = session.selectOne("selectone","2");
		//System.out.println(u.getByzi());
		System.out.println(list.size());
		List<Customer> list1=session.selectList("CustomerMapper.selectall");
		//UserEntity u = session.selectOne("selectone","2");
		//System.out.println(u.getByzi());
		System.out.println(list1.size());
		Customer c=new Customer();
		c.setId(2L);
		c.setUsername("yang");
		c.setPassword("yang");
		
		//int i = session.insert("CustomerMapper.insert",c);
		//session.commit();
		//System.out.println(i);
		
	}

}
