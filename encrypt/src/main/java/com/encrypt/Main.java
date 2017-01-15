package com.encrypt;




import java.util.Scanner;
import com.encrypt.Ciphers.Cipher;


public class Main {

public static void main(String[] args)
{
	Scanner in = new Scanner(System.in);
	FileManager file = new FileManager();
	UserOptions om = new UserOptions();
	EventTarget eventTraget = new EventTarget();
	int result;
	Cipher algorithm ;
	byte[] data = null;
	
	
	System.out.println("Select an algorithm");
	System.out.println("Press 1 for caesar algorithm");
	System.out.println("Press 2 for xor algorithm");
	System.out.println("press 3 for Multiplication algorithm");
	
	result = om.to_integer(in.nextLine());
	// selected algorithm by user
	algorithm = om.algorithms_menu(result);
	
	algorithm.addObserver(eventTraget);
	/*  action selected by user = {Encryption , Decryption }
	 * if user entered invalid selection he will be asked to enter new one
	 */
	EnumCipher option = om.eOrd(); 
	
	
	/*
	 * Checking if file path valid
	 * if not the user will be asked to enter again
	 */
	
	file = om.file_path();
	if((data = file.ReadBytes()) != null)
	{
			if(option.equals(EnumCipher.Encryption))
			{
				
				System.out.println("Your encryption key is: " + (int)algorithm.createKey());
				
				algorithm.setInput(data);
				
				// writing the encrypted bytes to file.encrypted
				if(file.writeBytesToFile(file.getFile().getPath() +".encrypted", algorithm.encrypt()) )
				{
					System.out.println("File saved at: " +file.getFilePathNoType()+".encrypted");
				}
				
				else
					System.out.println("Encryption failed!");
			}
			
			else if(option.equals(EnumCipher.Decryption))
			{
				System.out.println("Enter key:");
				
				algorithm.setKey((byte) om.to_integer( in.nextLine()));
				algorithm.setInput(data);
				
				try {
					if(file.writeStringTofile(file.getFilePathNoType()+"_decrypted"+"."+file.getFile().getPath().split("\\.")[1], algorithm.decrypt()))
							{
								System.out.println("File saved at: " +file.getFile().getPath().split("\\.")[0]+"_decrypted"+"."+file.getFile().getPath().split("\\.")[1]);
							}
					else
						System.out.println("Decryption failed!");
				} 
				catch (Exception e) 
				{
					System.out.println("Invalid Key!");
				}
			}
			
	}
  
	
	in.close();
		
}



	
}


