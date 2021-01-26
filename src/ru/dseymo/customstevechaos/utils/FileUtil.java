package ru.dseymo.customstevechaos.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {
	
	public static void copyFromJar(InputStream fileJar, File to) {
		
	    try (
	    		
	    	OutputStream out = new BufferedOutputStream(new FileOutputStream(to))) {
	 
	        byte[] buffer = new byte[1024];
	        int lengthRead;
	        while ((lengthRead = fileJar.read(buffer)) > 0) {
	        	
	            out.write(buffer, 0, lengthRead);
	            out.flush();
	            
	        }
	        
	    } catch (Exception e) {e.printStackTrace();}
		
	}
	
}
