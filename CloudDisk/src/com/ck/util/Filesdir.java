package com.ck.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ck.po.UserFile;

public class Filesdir {

	
	  public static void main(String[] args) throws FileNotFoundException, IOException { 
		 /* Date date = new Date(1308446382000L);
		  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String dddd = df.format(date);
		 System.out.println(dddd);*/
		  Filesdir fd=new Filesdir();
		 
			System.out.println(fd.deletefile("I:/1.png"));
		
	 
	  }
	 
	Map<String, UserFile> all = new HashMap<String, UserFile>();
	public  Map<String, UserFile> readfile(String filepath)
			throws Exception {
		File file = new File(filepath);
		GetFileSize g = new GetFileSize();
		if (!file.isDirectory()) {
		} else if (file.isDirectory()) {

			File[] filelist = file.listFiles();
			String fname="";
			for (int i = 0; i < filelist.length; i++) {
				UserFile f = new UserFile();
				f.setF_date(getDate(filelist[i].lastModified()));						
				//f.setSize(g.getsize(filelist[i]));
				f.setF_url(getfilepath(filelist[i].getPath()));			
				if (filelist[i].isDirectory()) {
					fname=filelist[i].getName() + "-isfloder";

				} else {

					fname=filelist[i].getName();
				}
				all.put(fname, f);

			}

		}
		return all;
	}

	
	Map<String, UserFile> voide = new HashMap<String, UserFile>();

	public  Map<String, UserFile> getvoidefile(String filepath)
			throws Exception {
		File file = new File(filepath);
		GetFileSize g = new GetFileSize();
		if (file.isDirectory()) {
			File[] filelist = file.listFiles();
			for (int i = 0; i < filelist.length; i++) {
				if (filelist[i].isDirectory()) {
					getvoidefile(filepath + "\\" + filelist[i].getName());
				} else {
					if (isvoide(filelist[i].getName())) {
						UserFile f = new UserFile();
						f.setF_date(getDate(filelist[i].lastModified()));						
						f.setF_size(g.getsize(filelist[i]));
						f.setF_url(getfilepath(filelist[i].getPath()));
						voide.put(filelist[i].getName(), f);
					}
				}
			}

		}

		return voide;
	}

	Map<String, UserFile> text = new HashMap<String, UserFile>();

	public  Map<String, UserFile> gettextfile(String filepath)
			throws Exception {
		File file = new File(filepath);
		GetFileSize g = new GetFileSize();
		if (file.isDirectory()) {
			File[] filelist = file.listFiles();
			for (int i = 0; i < filelist.length; i++) {
				if (filelist[i].isDirectory()) {
					gettextfile(filepath + "\\" + filelist[i].getName());
				} else {
					if (istext(filelist[i].getName())) {
						UserFile f = new UserFile();
						f.setF_date(getDate(filelist[i].lastModified()));						
						f.setF_size(g.getsize(filelist[i]));
						f.setF_url(getfilepath(filelist[i].getPath()));
						text.put(filelist[i].getName(), f);
					}
				}
			}

		}

		return text;
	}

	Map<String, UserFile> photo = new HashMap<String, UserFile>();

	public  Map<String, UserFile> getphotofile(String filepath)
			throws Exception {
		File file = new File(filepath);
		GetFileSize g = new GetFileSize();
		if (file.isDirectory()) {
			File[] filelist = file.listFiles();
			for (int i = 0; i < filelist.length; i++) {
				if (filelist[i].isDirectory()) {
					getphotofile(filepath + "\\" + filelist[i].getName());
				} else {
					if (isphoto(filelist[i].getName())) {
						UserFile f = new UserFile();
						f.setF_date(getDate(filelist[i].lastModified()));						
						//f.setSize(g.getsize(filelist[i]));
						f.setF_url(getfilepath(filelist[i].getPath()));
						photo.put(filelist[i].getName(), f);
					}
				}
			}

		}

		return photo;
	}

	public static String getfilepath(String path) {

		return path.substring(path.indexOf("fileupload") )
				.replace("\\", "/");
	}

	public static String getfiletype(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return null;
	}

	public static boolean isvoide(String str) {
		boolean retype = false;
		String type = GetTypeByHead.getExtensionName(str);
		String[] alltype = { "avi", "mp4", "mpeg", "mpg", "wav", "wma", "rm",
				"rmvb" };
		for (int i = 0; i < alltype.length; i++) {
			if (type.equals(alltype[i])) {
				retype = true;
			}
		}

		return retype;

	}

	public static boolean isphoto(String str) {
		boolean retype = false;
		String type = GetTypeByHead.getExtensionName(str);
		String[] alltype = { "jpg", "png", "jpeg", "jif", "psd" };
		for (int i = 0; i < alltype.length; i++) {
			if (type.equals(alltype[i])) {
				retype = true;
			}
		}

		return retype;

	}

	public static boolean istext(String str) {
		boolean retype = false;
		String type = GetTypeByHead.getExtensionName(str);
		String[] alltype = { "doc", "docx", "ppt", "txt", "xlsx", "pdf" };
		for (int i = 0; i < alltype.length; i++) {
			if (type.equals(alltype[i])) {
				retype = true;
			}
		}

		return retype;

	}
    public static String getDate(long date){
    	Date d = new Date(date);
    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	return  format.format(d);
    	
    }
	/**
	 * 删除某个文件夹下的所有文件夹和文件
	 */

	public boolean deletefile(String delpath) throws FileNotFoundException,
			IOException {
		boolean isdel=false;
		try {

			File file = new File(delpath);
			if (!file.isDirectory()) {
			
				file.delete();
				isdel=true;
			} else if (file.isDirectory()) {
				
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File delfile = new File(delpath + "\\" + filelist[i]);
					if (!delfile.isDirectory()) {
						
						delfile.delete();
						isdel=true;
					} else if (delfile.isDirectory()) {
						deletefile(delpath + "\\" + filelist[i]);
					}
				}
				file.delete();
				isdel=true;
               
			}

		} catch (FileNotFoundException e) {
			System.out.println("deletefile()   Exception:" + e.getMessage());
		}
		return isdel;
	}

	//移动
	public boolean removefile(String ppath,String path) {
			boolean isremove=true;
			Path pp=Paths.get(ppath);
			Path p=Paths.get(path);
			
			try {
				Files.move(p,pp.resolve(p.getFileName()),StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				
				isremove=false;
			}
			
			return isremove;
}
	public String renamefile(String dir, String name) {
		 String result="";
		 String oldname=dir.substring(dir.lastIndexOf("\\")+1);
		 String path=dir.substring(0, dir.lastIndexOf("\\"));
	            File oldfile=new File(dir); 
	            File newfile=new File(path+"\\"+name); 
	            if(!oldfile.exists()){
	                 result="notexists";
	            }
	            if(newfile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名 
	               { result="exists";}
	            else{ 
	                oldfile.renameTo(newfile); 
	                result="success";
	            } 
	       
		return result;

		
	}
	
	List<List> nodes = new ArrayList<List>();
	public List<List> getnodes(String dir,String pId) {
		File file = new File(dir);
		File[] filelist = file.listFiles();
		List s=new ArrayList();
		for (int j = 0; j < filelist.length; j++) {
			if (filelist[j].isDirectory()) {
			   s.add(filelist[j].getName());
			}
				
		}
		
		for (int i = 0; i < s.size(); i++) {
			
				List l=new ArrayList();
				if(pId.equals("0")){
					l.add(Long.parseLong(String.valueOf(i+1)));
					l.add(Long.parseLong(pId));
					l.add(s.get(i));
					nodes.add(l);
					getnodes(dir +"\\"+s.get(i),String.valueOf(i+1));
				}else{
					l.add(Long.parseLong(pId+String.valueOf(i+1)));
					l.add(Long.parseLong(pId));
					l.add(s.get(i));
					nodes.add(l);
					getnodes(dir +"\\"+s.get(i),pId+String.valueOf(i+1));
				}
				
			
		}
		
		return nodes;
	}

}
