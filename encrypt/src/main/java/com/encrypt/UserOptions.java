package com.encrypt;

import java.io.File;
import java.util.Scanner;

import com.encrypt.Ciphers.CaesarCipher;
import com.encrypt.Ciphers.Cipher;
import com.encrypt.Ciphers.DoubleCipher;
import com.encrypt.Ciphers.MWOCipher;
import com.encrypt.Ciphers.ReverseCipher;
import com.encrypt.Ciphers.SplitCipher;
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
	 * Prints the menu content 
	 */
	public void menu_content()
	{
		System.out.println("Select an algorithm");
		System.out.println("Press 1 for caesar algorithm");
		System.out.println("Press 2 for xor algorithm");
		System.out.println("press 3 for Multiplication algorithm");
		System.out.println("press 4 for Double algorithm");
		System.out.println("press 5 for Reverse algorithm");
		System.out.println("press 6 for Split algorithm");
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
			
			if( result>=1 && result <=6  )
				status = true;
			
			else
			{
				System.out.println("Please enter ONLY numbers as shown");
				this.menu_content();
				result = to_integer(input.nextLine());
			}
		}
		
		if(result == 1 )
			return (new CaesarCipher());
		
		else if(result == 2)
			return (new XorCipher());
		
		else if(result == 3)
			return (new MWOCipher());
		
		else if (result == 4)
			return (new DoubleCipher(secondCipher("first"),secondCipher("second")));
		
		else if(result == 5)
			return (new ReverseCipher(secondCipher("one"),null));
		
		else 
		{
			Cipher c =  secondCipher("one");
			return (new SplitCipher(c,c));
		}
			
		
	}
	
	/***
	 * If the user wants sync or async operation
	 * @return operation selected
	 */
	public int syncOrasync()
	{
		int result = 0;
		boolean status = false ;
		while(!status)
		{
				System.out.println("please select type of operation:");
				System.out.println("Press 1 for sync operation");
				System.out.println("Press 2 for async operation");
	
				result = to_integer(input.nextLine());
				
				if( result == 1 || result == 2 )
					status = true;
				
				else
				{
					System.out.println("Please enter ONLY numbers as shown");
				
				}
		}
		
		return result;
	}
	/***
	 * Secondary ciphers for the larger ones
	 * @param num - number of wanted secnodaries
	 * @return the secondary cipher
	 */
	private Cipher secondCipher(String num)
	{
		int result = 0;
		boolean status = false ;
		while(!status)
		{
				System.out.println("please select "+num+ " secondary cipher:");
				System.out.println("Press 1 for caesar algorithm");
				System.out.println("Press 2 for xor algorithm");
				System.out.println("press 3 for Multiplication algorithm");
				
				result = to_integer(input.nextLine());
				
				if( result == 1 || result == 2 || result == 3 )
					status = true;
				
				else
				{
					System.out.println("Please enter ONLY numbers as shown");
				
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
	/***
	 * Getting the folder from the user 
	 * @return file object with folder data
	 */
	public File getFolder()
	{
		boolean status = false ; 
		File f = null ;
		String path;
		while(!status)
		{
			System.out.println("Enter folder path");
			path = input.nextLine();
			try
			{
				f = new File(path);
				if(!f.exists())
					throw(new Exception()); 
				status = true;
			}
			
			catch(Exception e)
			{
				System.out.println("Please enter a valid path");
			}
			
		}
		return f;
		
	}
	
	/***
	 * Sub menu for the user
	 * @return 1 for one file and 2 for more than one file
	 */
	@SuppressWarnings("resource")
	public int OneOrMore()
	{
		int result = -1 ; 
		while(result == -1 )
		{
		System.out.println("Enter 1 for one file action");
		System.out.println("Enter 2 for more than one file action");
		
		result = to_integer(new Scanner(System.in).nextLine());
		if( result != 1 && result != 2)
		{
			result = -1 ;
			System.out.println("Please enter on the allowed selection");
		}
		}
		
		return result;
	}

}
