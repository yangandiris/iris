package com.ck.util;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;




public class Imagefix {

	
	 public static boolean compressPic(String srcFilePath, String descFilePath,float quality)  
	    {  
	        File file = null;  
	        BufferedImage src = null;  
	        FileOutputStream out = null;  
	        ImageWriter imgWrier;  
	        ImageWriteParam imgWriteParams;  
	  
	        // 指定写图片的方式为 jpg  
	        imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();  
	        imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(null);  
	        // 要使用压缩，必须指定压缩方式为MODE_EXPLICIT  
	        imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);  
	        // 这里指定压缩的程度，参数qality是取值0~1范围内，  
	        imgWriteParams.setCompressionQuality(quality);  
	        imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);  
	        ColorModel colorModel = ColorModel.getRGBdefault();  
	        // 指定压缩时使用的色彩模式  
	        imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel, colorModel  
	                .createCompatibleSampleModel(16, 16)));  
	  
	        try  
	        {  
	          
	                file = new File(srcFilePath);  
	                src = ImageIO.read(file);  
	                out = new FileOutputStream(descFilePath);  
	  
	                imgWrier.reset();  
	                // 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何 OutputStream构造  
	                imgWrier.setOutput(ImageIO.createImageOutputStream(out));  
	                // 调用write方法，就可以向输入流写图片  
	                imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);  
	                out.flush();  
	                out.close();  
	            
	        }  
	        catch(Exception e)  
	        {  
	            e.printStackTrace();  
	            return false;  
	        }  
	        return true;  
	    }  
	
	
}
