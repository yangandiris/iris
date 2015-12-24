package com.ck.util;

import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;

import javax.activation.MimetypesFileTypeMap;

public class GetTypeByHead {
	
	 public static String getExtensionName(String filename) {   
	        if ((filename != null) && (filename.length() > 0)) {   
	            int dot = filename.lastIndexOf('.');   
	            if ((dot >-1) && (dot < (filename.length() - 1))) {   
	                return filename.substring(dot + 1);   
	            }   
	        }   
	        return "unknown";   
	    }   
	/* 
	 * Java文件操作 获取不带扩展名的文件名 
	 * 
	 *  Created on: 2011-8-2 
	 *      Author: blueeagle 
	 */  
	    public static String getFileNameNoEx(String filename) {   
	        if ((filename != null) && (filename.length() > 0)) {   
	            int dot = filename.lastIndexOf('.');   
	            if ((dot >-1) && (dot < (filename.length()))) {   
	                return filename.substring(0, dot);   
	            }   
	        }   
	        return filename;   
	    }   
	
	 public static void main(String[] args) throws IOException {
		 String a="asdas.asd";
		  System.out.println(getExtensionName("e:/学习ghf资料/玛利亚mp4"));  
	}
	 
	}