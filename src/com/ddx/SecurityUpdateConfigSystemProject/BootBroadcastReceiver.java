package com.ddx.SecurityUpdateConfigSystemProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootBroadcastReceiver extends BroadcastReceiver {
	static final String ACTION = "android.intent.action.BOOT_COMPLETED";  //��Ҫ��������android.permission.RECEIVE_BOOT_COMPLETEDȨ��
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if (intent.getAction().equals(ACTION)){  
			   //Intent copyIntent=new Intent(context,CopyActivity.class);  
			   //copyIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);			  
			   //context.startActivity(copyIntent);  
			copyFile("/data/smartpos_update.ini", "/sdcard/smartpos_update.ini");
		}  
	}
	
	
	/** 
     * ���Ƶ����ļ� 
     * @param oldPath String ԭ�ļ�·�� �磺c:/fqf.txt 
     * @param newPath String ���ƺ�·�� �磺f:/fqf.txt 
     * @return boolean 
     */ 
   public void copyFile(String oldPath, String newPath) { 
       try { 
           int bytesum = 0; 
           int byteread = 0; 
           File oldfile = new File(oldPath); 
           if (oldfile.exists()) { //�ļ�����ʱ 
               InputStream inStream = new FileInputStream(oldPath); //����ԭ�ļ� 
               FileOutputStream fs = new FileOutputStream(newPath); 
               byte[] buffer = new byte[1444]; 
               int length; 
               while ( (byteread = inStream.read(buffer)) != -1) { 
                   bytesum += byteread; //�ֽ��� �ļ���С 
                   System.out.println(bytesum); 
                   fs.write(buffer, 0, byteread); 
               } 
               inStream.close(); 
           } 
       } 
       catch (Exception e) { 
           System.out.println("���Ƶ����ļ���������"); 
           e.printStackTrace(); 
       } 
   } 
   /** 
     * ���������ļ������� 
     * @param oldPath String ԭ�ļ�·�� �磺c:/fqf 
     * @param newPath String ���ƺ�·�� �磺f:/fqf/ff 
     * @return boolean 
     */ 
   public void copyFolder(String oldPath, String newPath) { 
       try { 
           (new File(newPath)).mkdirs(); //����ļ��в����� �������ļ��� 
           File a=new File(oldPath); 
           String[] file=a.list(); 
           File temp=null; 
           for (int i = 0; i < file.length; i++) { 
               if(oldPath.endsWith(File.separator)){ 
                   temp=new File(oldPath+file[i]); 
               } 
               else{ 
                   temp=new File(oldPath+File.separator+file[i]); 
               } 
               if(temp.isFile()){ 
                   FileInputStream input = new FileInputStream(temp); 
                   FileOutputStream output = new FileOutputStream(newPath + "/" + 
                           (temp.getName()).toString()); 
                   byte[] b = new byte[1024 * 5]; 
                   int len; 
                   while ( (len = input.read(b)) != -1) { 
                       output.write(b, 0, len); 
                   } 
                   output.flush(); 
                   output.close(); 
                   input.close(); 
               } 
               if(temp.isDirectory()){//��������ļ��� 
                   copyFolder(oldPath+"/"+file[i],newPath+"/"+file[i]); 
               } 
           } 
       } 
       catch (Exception e) { 
           System.out.println("���������ļ������ݲ�������"); 
           e.printStackTrace(); 
       } 
   }

}
