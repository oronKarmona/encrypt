package com.encrypt;

import java.io.File;
import java.util.Scanner;

import com.encrypt.Ciphers.CaesarCipher;
import com.encrypt.Ciphers.Cipher;
import com.encrypt.Ciphers.MWOCipher;
import com.encrypt.Ciphers.XorCipher;
/***
 * This class is used to show options for the user
 * @author Oron
 *
 */
public class UserOptions {
	Scanner input;
	
	/***
	 * Constructor
	 */
	public UserOptions()
	{
		input = new Scanner(System.in);
	}
	
	/***
	 * Parsing string to integer if possible. If not return error value (-1)
	 * @param input - user input
	 * @return - integer value of the input
	 */
public int to_integer(String input)
{
	int i ; 
	try{
		 i =  Integer.parseInt(input);
		 return i ;
	}
	
	catch(Exception e)
	{
		return -1;
	}
}
	
	/**
	 * Showing the user a menu where he can choose a cipher algorithm
	 * @return cipher object
	 */
	Cipher algorithms_menu(int result)
	{
		boolean status = false;
		while(!status)
		{
			
			if( result == 1 || result == 2 || result == 3 )
				status = true;
			
			else
			{
				System.out.println("Please enter ONLY numbers as shown");
				System.out.println("Select an algorithm");
				System.out.println("Press 1 for caesar algorithm");
				System.out.println("Press 2 for xor algorithm");
				System.out.println("press 3 for Multiplication algorithm");
				
				result = to_integer(input.nextLine());
			}
		}
		
		if(result == 1 )
			return (new CaesarCipher());
		
		else if(result == 2)
			return (new XorCipher());
		else
			return (new MWOCipher());
			
		
	}
	
	/***
	 * Checks if the user wants encryption or decryption
	 * @return enum represents the selected option
	 */
	public EnumCipher eOrd()
	{
		int result = 0 ; 
		boolean status = false;
		while(!status)
		{
			System.out.println("Press 1 for encryption");
			System.out.println("Press 2 for decryption");
			
			result = to_integer(input.nextLine());
			
			if( result == 1 || result == 2  )
				status = true;
			else
				System.out.println("Please enter ONLY numbers as shown");
		}
		
		if(result == 1 )
			return EnumCipher.Encryption;
		
		else 
			return EnumCipher.Decryption;
		
	}
	
	/***
	 * Gets file from its path as inserted by the user
	 * @return FileManager object which contains the file
	 */
	public FileManager file_path()
	{
		boolean status = false ; 
		String path ; 
		File f = null ;
		FileManager file = new FileManager();
		
		while(!status)
		{
			System.out.println("Enter file path");
			path = input.nextLine();
			try
			{
				file.setFile(new File(path));
				if(!file.getFile().exists())
					throw(new Exception()); 
				status = true;
			}
			
			catch(Exception e)
			{
				System.out.println("Please enter a valid path");
			}
			
		}
		
		return file;
	}

}
