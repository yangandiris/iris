package com.ck.test;

public class Singleton2{
private Singleton2(){}
private static Singleton2 single=null;
//静态工厂方法
public synchronized static Singleton2 getInstance(){
if(single==null){
single=new Singleton2();
}
  return single;
}
}
