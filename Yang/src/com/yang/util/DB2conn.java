package com.yang.util;
import java.sql.*;

public class DB2conn{
 /**设置参数**/
  private static Connection conn = null;
  private static Statement stmt = null;
  private static ResultSet rs = null;


  public DB2conn() {
             try{
             System.out.println("正在连接数据库..........");
             Class.forName("com.ibm.db2.jcc.DB2Driver");//加载mysql驱动程序�?
             String url = "jdbc:db2://127.0.0.1:50000/YANG";//url为连接字符串
             String user = "db2admin";//数据库用户名
             String pwd = "admin";//数据库密�?
             conn=(Connection)DriverManager.getConnection(url,user,pwd);
             System.out.println("数据库连接成功！！！");
             }catch(Exception e){ 
              System.out.println(e.getMessage());
              //e.printStackTrace();
             }
  }
  public static void main(String[] args) throws SQLException {
		   DB2conn a = new DB2conn();//实例化对象，作用是调用构造方�?
		   a.getClass();//无意�?
		 /**查询语句**/
		   String sql="select * from test";
		   stmt = (Statement) conn.createStatement();
		   stmt.execute(sql);//执行select语句用executeQuery()方法，执行insert、update、delete语句用executeUpdate()方法�?
		   rs=(ResultSet) stmt.getResultSet();
		   while(rs.next()){ //当前记录指针移动到下�?条记录上
		    String name =rs.getString(1);//得到第二个字�?(name)的�??
		    String psw = rs.getString("byzi");//得到(password)的�??
		    System.out.println(name+" "+psw);
		   }
		  rs.close();//后定义，先关�?
		  stmt.close();
		  conn.close();//先定义，后关�?
		  }
 }