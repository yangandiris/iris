package com.ck.test;

import java.io.File;
import java.io.IOException;

public class Filetest {

	public static void main(String[] args) throws IOException {
		File f=new File("F:/yang/IT资料/APIS","yang.txt");
		//f.createNewFile();
		System.out.println(f.exists());
		System.out.println(f.getName());
		System.out.println(f.getPath());
	}
}
