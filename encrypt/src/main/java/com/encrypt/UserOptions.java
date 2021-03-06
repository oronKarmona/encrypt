package com.encrypt;

import java.io.File;
import java.util.Scanner;

import org.apache.log4j.Logger;

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
	 * Logger object
	 */
	final Logger log = Logger.getLogger(UserOptions.class);
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
		{
			log.info("CaesarCipher has been selected");
			return (new CaesarCipher());
		}
		else if(result == 2)
		{
			log.info("XorCipher has been selected");
			return (new XorCipher());
		}
			
		
		else if(result == 3)
		{
			log.info("MWOCipher has been selected");
			return (new MWOCipher());
		}
			
		
		else if (result == 4)
		{
			log.info("DoubleCipher has been selected");
			return (new DoubleCipher(secondCipher("first"),secondCipher("second")));
		}
		
		else if(result == 5)
		{
			log.info("ReverseCipher has been selected");
			return (new ReverseCipher(secondCipher("one"),null));
		}
		
		else 
		{
			log.info("SplitCipher has been selected");
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
		{
			log.info("Secondary cipher - CaesarCipher has been selected");
			return (new CaesarCipher());
		}
		
		else if(result == 2)
		{
			log.info("Secondary cipher - XorCipher has been selected");
			return (new XorCipher());
		}
		
		else 
		{
			log.info("Secondary cipher - MWOCipher has been selected");
			return (new MWOCipher());
		}

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
		String path = ""; 
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
		log.info("file path selected -" + path);
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
		String path = "";
		while(!status)
		{
			System.out.println("Enter folder path");
			path = input.nextLine();
			
				f = new File(path);
				if(!f.exists())
				{
					System.out.println("Folder " + path + " doesn't exist");
					status = false ;
				}
				else 
					status = true;
		}
		
		log.info("folder path selected -" + path);
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
			System.out.println("Please enter only the allowed selection");
		}
		}
		
		return result;
	}
	
	
	/***
	 * Showing the user the option to use the default cipher or another one
	 * @return true for default and false otherwise
	 */
	@SuppressWarnings("resource")
	public boolean defaultOrNot()
	{
		int result = -1 ; 
		String str = "";
		while(result == -1 )
		{
			
		System.out.println("Enter y for the default cipher");
		System.out.println("Enter n for using different cipher");
		str = new Scanner(System.in).nextLine();
		
			if( !str.equals("y") && !str.equals("n"))
			{
				result = -1 ;
				System.out.println("Please enter only the allowed selection");
			}
			else
				result = 1 ;
		}
		if(str.equals("y"))
			return true;
		else return false;
		
	}
	
	
	public boolean exportCipher()
	{
		int result = -1 ; 
		String str = "";
		while(result == -1 )
		{
			
		System.out.println("Enter y to export the cipher to the config.xml file \n and use it as default cipher next time");
		System.out.println("Enter n otherwise");
		str = new Scanner(System.in).nextLine();
		
			if( !str.equals("y") && !str.equals("n"))
			{
				result = -1 ;
				System.out.println("Please enter only the allowed selection");
			}
			else
				result = 1 ;
		}
		if(str.equals("y"))
			return true;
		else return false;
		
	}

}
