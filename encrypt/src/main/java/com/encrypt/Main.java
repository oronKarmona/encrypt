package com.encrypt;




import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import jaxb.JAXBCon;

import org.apache.log4j.Logger;

import lombok.extern.log4j.Log4j;
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
	byte[] data = null;
	boolean s_result ;
	
	ErrorMSG.msg = "";
	ErrorMSG.ecxeption = "";
	
	
	final Logger log = Logger.getLogger(Main.class);
	log.info("----------------------------------------New RUN-------------------------------------------");
	//Retrieving the default cipher
	Cipher algorithm = JAXBCon.unmarshall();
	//Cipher algorithm = null ;
	
	
	//default cipher or not
	s_result = uo.defaultOrNot();
	
	if(!s_result) // if the user wants cipher the is not the default
	{
		uo.menu_content();
		result = uo.to_integer(in.nextLine());
		// selected algorithm by user
		algorithm = uo.algorithms_menu(result);
		
		s_result = uo.exportCipher();
		if(s_result)
			JAXBCon.MarshallCipher(algorithm);
	}
	
	algorithm.addObserver(eventTraget);
	/*  action selected by user = {Encryption , Decryption }
	 * if user entered invalid selection he will be asked to enter new one
	 */
	EnumCipher option = uo.eOrd(); 
	
	//key creation in case of encryption
	if(option.equals(EnumCipher.Encryption))
	{
		log.info("Encryption selected");
		algorithm.createKey();
		log.info("Key created");
	}
	
	//key request in case of decryption
	else if (option.equals(EnumCipher.Decryption))
	{
		log.info("Decryption selected");
		  System.out.println("Enter key path:");
		  ArrayList<Byte> keys = new ArrayList<Byte>() ; 
		  byte[] k = null;
		  try{
			  String p = in.nextLine();
			  k = file.ReadBytes(p);
			  
		  }catch (Exception e)
		  {
			  System.out.println("key File not found!");
			  e.printStackTrace();
			  
			  ErrorMSG.addEx("key File not found!", e.getMessage());
			  log.error(ErrorMSG.msg);
			  log.error(ErrorMSG.ecxeption);
			  
		  }
		
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
			
			log.info("Key has been set");
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
				log.info("Sync operation has been selected");
				Sync.action();
				log.info("Sync operation has been finished");
				break;
				
			case 2:
				Async.folder = folder;
				Async.c = algorithm;
				Async.option = option;
				log.info("ASync operation has been selected");
				try {
					Async.start();
					} catch (CloneNotSupportedException e) {
						
						ErrorMSG.addEx("Failed to use async operation", e.getMessage());
						System.out.println("Failed to use async operation");
						e.printStackTrace();
						
						log.error(ErrorMSG.msg);
						log.error(ErrorMSG.ecxeption);
						
					}
				
				log.info("ASync operation has been finished");	
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
			algorithm.setFilePath(file.getFile().getPath());
			if(algorithm instanceof AbstractDouble)
				((AbstractDouble)algorithm).setScondaryCipherFilePath();
			
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
						System.out.println("Encryption failed!");
						ErrorMSG.addEx("Encryption failed!", e.getMessage());
						
						log.error(ErrorMSG.msg);
						log.error(ErrorMSG.ecxeption);
						
					}
					
				}
				
				else if(option.equals(EnumCipher.Decryption))
				{
					
					algorithm.setInput(data);
					try {
						algorithm.decrypt();
					} catch (Exception e1) {
						e1.printStackTrace();
						System.out.println("Decryption failed!");
						ErrorMSG.addEx("Decryption failed!", e1.getMessage());
						
						log.error(ErrorMSG.msg);
						log.error(ErrorMSG.ecxeption);
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
						System.out.println("Write the decrypted file has failed" + file.getFilePathNoType()+"_decrypted"+"."+file.getFile().getPath().split("\\.")[1]);
						e.printStackTrace();
						ErrorMSG.addEx("Decryption failed!", e.getMessage());
						
						log.error(ErrorMSG.msg);
						log.error(ErrorMSG.ecxeption);
						
					}
					
				
					
					
				}
				
		}
	  
	}
	
	
	in.close();
		
}



	
}


