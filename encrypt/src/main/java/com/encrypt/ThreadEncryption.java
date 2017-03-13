package com.encrypt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import com.encrypt.Ciphers.Cipher;

public class ThreadEncryption implements Runnable{
	
	File folder ; 
	Cipher alg ; 
	ArrayList<File> listOfFiles;
	String target;
	FileManager fm ; 
	ThreadEncryption(File folder , Cipher alg, String target)
	{
		this.folder = folder ; 
		this.alg  = alg; 
		this.target = target;
		fm = new FileManager();
		
		listOfFiles = new ArrayList<File>();
		
		for(File file : folder.listFiles())
		{
			if (file.isFile())
			{
				listOfFiles.add(file);
			}
			
		}
	}

	@Override
	public void run() 
	{
		byte[] data = null; 
		
		for(int i = 0 ; i < listOfFiles.size() ; i++)
		{
			System.out.println("-------"+ (i+1)+ "-------");
						try {
							data = Files.readAllBytes(listOfFiles.get(i).toPath());
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						alg.setInput(data);
						
						try {
							data = alg.encrypt();
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						fm.writeBytesToFile(target+"\\"+listOfFiles.get(i).getName()+".encrypted", data);
		}
		
		System.out.println("All files are saved at "+target);
		
	}

}
