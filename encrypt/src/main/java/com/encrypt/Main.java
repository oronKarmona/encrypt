package com.encrypt;




import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import MultiFiles.Async;
import MultiFiles.Sync;
import MultiFiles.SyncThread;

import com.encrypt.Ciphers.AbstractDouble;
import com.encrypt.Ciphers.Cipher;


public class Main {

public static void main(String[] args)
{

	Scanner in = new Scanner(System.in);
	FileManager file = new FileManager();
	UserOptions uo = new UserOptions();
	EventTarget eventTraget = new EventTarget();
	int result;
	Cipher algorithm ;
	byte[] data = null;
	
	
	uo.menu_content();
	result = uo.to_integer(in.nextLine());
	// selected algorithm by user
	algorithm = uo.algorithms_menu(result);
	
	algorithm.addObserver(eventTraget);
	/*  action selected by user = {Encryption , Decryption }
	 * if user entered invalid selection he will be asked to enter new one
	 */
	EnumCipher option = uo.eOrd(); 
	
	//key creation in case of encryption
	if(option.equals(EnumCipher.Encryption))
		algorithm.createKey();
	
	//key request in case of decryption
	else if (option.equals(EnumCipher.Decryption))
	{
		  System.out.println("Enter key path:");
		  ArrayList<Byte> keys = new ArrayList<Byte>() ; 
		 byte[] k = file.ReadBytes(in.nextLine());
		  for(byte b: k)
			  keys.add(b);
		 
	      
			if(algorithm instanceof AbstractDouble)
			{
				(((AbstractDouble)algorithm)).setKeys(keys);
				 
			}
			else
			{
				algorithm.decryptionKey(keys);
			}
			
	}
			
	int oneOrmore = uo.OneOrMore(); // if the user want to encrypt one or more files
	
	// manipulation of more than one
	if(oneOrmore == 2 )
	{
		File folder = uo.getFolder();
		
		switch(uo.syncOrasync())
		{
			case 1:
				Sync.folder = folder;
				Sync.c = algorithm;
				Sync.option = option;
				Sync.action();
				break;
				
			case 2:
				Async.folder = folder;
				Async.c = algorithm;
				Async.option = option;
				try {
					Async.start();
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				break;
		}
		
		
	}
	
	
	
	
	// manipulation of one file 
	else if(oneOrmore == 1)
	{
		/*
		 * Checking if file path valid
		 * if not the user will be asked to enter again
		 */
		
		file = uo.file_path();
	//	long start_time = System.nanoTime();
		if((data = file.ReadBytes(file.getFile().getPath())) != null)
		{
				if(option.equals(EnumCipher.Encryption))
				{
					file.writeBytesToFile(file.getOnlyPath()+"\\key.bin", algorithm.getByteArrayKey());
					algorithm.setInput(data);
					
					// writing the encrypted bytes to file.encrypted
					try {
						if(file.writeBytesToFile(file.getFile().getPath() +".encrypted", algorithm.encrypt()) )
						{
							System.out.println("File saved at: " +file.getFile().getPath()+".encrypted");
						}
						
						else
							System.out.println("Can't write to file");
					//	long total_time = System.nanoTime() - start_time;
			//			System.out.println( " Finished - Total time: " + ((total_time)/(double)1000000) +" ms");	
					} 
					catch (Exception e) {
						e.printStackTrace();
						System.out.println("Encryption error!");
					}
				}
				
				else if(option.equals(EnumCipher.Decryption))
				{
					
					algorithm.setInput(data);
					try {
						algorithm.decrypt();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					try {
						if(file.writeBytesToFile(file.getFilePathNoType()+"_decrypted"+"."+file.getFile().getPath().split("\\.")[1], algorithm.getOutput()))
								{
									System.out.println("File saved at: " +file.getFile().getPath().split("\\.")[0]+"_decrypted"+"."+file.getFile().getPath().split("\\.")[1]);
								}
						else
							System.out.println("Can't write to file");
					} 
					catch (Exception e) 
					{
						System.out.println("Invalid Key!");
					}
					
					
				}
				
		}
	  
	}
	in.close();
		
}



	
}


