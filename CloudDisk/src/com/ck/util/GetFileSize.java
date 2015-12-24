package com.ck.util;
import java.io.File;
import java.text.DecimalFormat;
import java.io.FileInputStream; 
public class GetFileSize
 {
     public long getFileSizes(File f) throws Exception{//取得文件大小
      
        return f.length();
     }
     // 递归
    public long getFileSize(File f)throws Exception//取得文件夹大小
    {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++)
        {
            if (flist[i].isDirectory())
            {
                size = size + getFileSize(flist[i]);
            } else
            {
                size = size + flist[i].length();
            }
        }
        return size;
     }

    public String FormetFileSize(long fileS) {//转换文件大小
       DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) +"G";
        }
        return fileSizeString;
     }
    
     public long getlist(File f){//递归求取目录文件个数
        long size = 0;
        File flist[] = f.listFiles();
        size=flist.length;
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getlist(flist[i]);
                size--;
            }
        }
        return size;
  
   }
     public String getsize(File f) throws Exception{
    	 String size = "";             
         if (f.isDirectory()) { //如果路径是文件夹的时候
           
        	 size = FormetFileSize(getFileSize(f));
            
         } else {
            
        	 size =FormetFileSize( getFileSizes(f));
             
         }	 
    	 return size;
    	 
     }
    
 }
