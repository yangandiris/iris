package com.ck.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ck.dao.UserDao;
import com.ck.dao.UserFileDao;
import com.ck.po.UserFile;
import com.ck.util.Imagefix;

/**
 * Servlet implementation class Upload
 */
@WebServlet("/Upload")
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int BUFFER_SIZE =1024;
	 @Autowired
		private UserFileDao userFileDao;
	    @Autowired
	    private UserDao userDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        AutowireCapableBeanFactory factory = ctx.getAutowireCapableBeanFactory();
        factory.autowireBean(this);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletContext servletContext = this.getServletConfig().getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding(request.getCharacterEncoding());
		upload.setSizeMax(1024 * 1024 * 1024 * 2);
        String path=request.getServletContext().getRealPath("/fileupload");
		String parent=null,md5=null,chunks=null,chunk=null,size=null;
		File ifile =new File(path);    
		//如果文件夹不存在则创建    
		if  (!ifile .exists()  && !ifile .isDirectory())      
		{  
		    ifile .mkdir();    
		} 
        int ret=0;
	          try {
				List<FileItem> list=upload.parseRequest(request);
				for(FileItem item:list){
					if(item.isFormField()){
						String name = item.getFieldName();  
						if(name.equals("parent")){
							parent=item.getString("UTF-8");
						}
						if(name.equals("md5")){
							md5=item.getString();
						}
						if(name.equals("chunks")){
							chunks=item.getString();
						}
						if(name.equals("chunk")){
							chunk=item.getString();
						}
						if(name.equals("size")){
							size=item.getString();
						}
					}else{
					    UserFile uf=new UserFile();
						uf.setF_name(item.getName());
						uf.setF_date(getDate());
						uf.setF_size(FormetFileSize(Long.parseLong(size)));
						uf.setF_type(item.getContentType());
						uf.setF_parent(parent);
						uf.setIsfolder(0);
						uf.setMd5(md5);
						uf.setF_url("fileupload/"+md5+"-"+item.getName());
						if(parent.indexOf("/")!=-1){
							uf.setC_id(userDao.getidbyname(parent.substring(0, parent.indexOf("/"))));
						}else{
							uf.setC_id(userDao.getidbyname(parent));
						}
						
						if(chunk!=null){
							if(Integer.parseInt(chunk)==0){
								File file=new File(path,item.getName());
								if(file.exists()){
									file.delete();
								}
							}
							if(Integer.parseInt(chunk)==Integer.parseInt(chunks)-1){
								appendFile(item.getInputStream(),new File(path,md5+"-"+item.getName()));
								userFileDao.saveUserFile(uf);
							}else{
								appendFile(item.getInputStream(),new File(path,md5+"-"+item.getName()));
							}
							ret=1;
						}else{
							try {
								item.write(new File(path,md5+"-"+item.getName()));
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							userFileDao.saveUserFile(uf);
							float quality=1;
							if(item.getContentType().equals("image/jpeg")){
								File imgfile =new File(path+"/imgfix");    
								//如果文件夹不存在则创建    
								if  (!imgfile .exists()  && !imgfile .isDirectory())      
								{  
								    imgfile.mkdir();    
								}  
								
								if(Long.parseLong(size)>1024*300&&Long.parseLong(size)<1024*1024){
									quality=(float) 0.5;
								}else if(Long.parseLong(size)>1024*1024&&Long.parseLong(size)<1024*1024*3){
									quality=(float) 0.2;
								}else{
									quality=(float) 0.1;
								}
								Imagefix.compressPic(path+"/"+md5+"-"+item.getName(), path+"/imgfix/"+md5+"-"+item.getName(), quality);
								
							}
							ret=1;
							
						}
					}
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	          response.setContentType("text/html");
	          PrintWriter out = response.getWriter();
	          out.println(ret);
	          out.flush();
	          out.close();
	}
	
	public static String getDate(){
    	Date d = new Date();
    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	return  format.format(d);
    	
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	//分片上传处理
	 private void appendFile(InputStream in, File destFile) {
	        OutputStream out = null;
	        try {
	            if (destFile.exists()) {
	                out = new BufferedOutputStream(new FileOutputStream(destFile, true), BUFFER_SIZE); 
	            } else {
	                out = new BufferedOutputStream(new FileOutputStream(destFile),BUFFER_SIZE);
	            }
	            in = new BufferedInputStream(in, BUFFER_SIZE);
	             
	            int len = 0;
	            byte[] buffer = new byte[BUFFER_SIZE];          
	            while ((len = in.read(buffer)) > 0) {
	                out.write(buffer, 0, len);
	            }
	        } catch (Exception e) {
	           System.out.println(e.getMessage());
	        } finally {     
	            try {
	                if (null != in) {
	                    in.close();
	                }
	                if(null != out){
	                    out.close();
	                }
	            } catch (IOException e) {
	            	System.out.println(e.getMessage());
	            }
	        }
	    }

}
