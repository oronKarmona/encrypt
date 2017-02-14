package com.encrypt;




import java.util.ArrayList;
import java.util.Scanner;

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
	
	
	
	int oneOrmore = uo.OneOrMore(); // if the user want to encrypt one or more files
	
	if(oneOrmore == 1)
	{
		/*
		 * Checking if file path valid
		 * if not the user will be asked to enter again
		 */
		
		file = uo.file_path();
		if((data = file.ReadBytes(file.getFile().getPath())) != null)
		{
				if(option.equals(EnumCipher.Encryption))
				{
					
					
					algorithm.createKey();
					file.writeBytesToFile(file.getOnlyPath()+"\\key.bin", algorithm.getByteArrayKey());
					//file.writeKeytoFile(file.getOnlyPath()+"\\key.bin",algorithm.getKeys());
					algorithm.setInput(data);
					
					// writing the encrypted bytes to file.encrypted
					try {
						if(file.writeBytesToFile(file.getFile().getPath() +".encrypted", algorithm.encrypt()) )
						{
							System.out.println("File saved at: " +file.getFile().getPath()+".encrypted");
						}
						
						else
							System.out.println("Can't write to file");
						
					} 
					catch (Exception e) {
						e.printStackTrace();
						System.out.println("Encryption error!");
					}
				}
				
				else if(option.equals(EnumCipher.Decryption))
				{
					  System.out.println("Enter key path:");
					  ArrayList<Byte> keys = new ArrayList<Byte>() ; 
					  byte[] k = file.ReadBytes(in.nextLine());
					  for(byte b: k)
						  keys.add(b);
				    //  ArrayList<Byte> keys = file.readKeyFromFile(in.nextLine());
				      
						if(algorithm instanceof AbstractDouble)
						{
							(((AbstractDouble)algorithm)).setKeys(keys);
							 
						}
						else
						{
							algorithm.decryptionKey(keys);
						}
						
					algorithm.setInput(data);
					
					try {
						if(file.writeStringTofile(file.getFilePathNoType()+"_decrypted"+"."+file.getFile().getPath().split("\\.")[1], algorithm.decrypt()))
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


