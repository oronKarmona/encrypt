package com.encrypt;



import java.io.File;
import java.util.Scanner;

public class Main {

public static void main(String[] args)
{
	String instruction = "error";
	int input ; 
	boolean status = false;
	Scanner in = new Scanner(System.in);
	InputChecker ic = new InputChecker();
	FileManager file = new FileManager();
	String path ; 
	String file_content ; 
	CaesarCipher cc = new CaesarCipher();
	byte[] data = null;

	/*  action selected by user = {Encryption , Decryption }
	 * if user entered invalid selection he will be asked to enter new one
	 */
	while(!status)
	{
		System.out.print("Press 1: for encryption " + "\n" + "press 2:for decryption"+"\n");
		
		instruction = in.nextLine();
		input = ic.to_integer(instruction);
		
		if(input == -1)
		{
			System.out.println("Enter only number value");
			instruction = "error"; // error
		}
		else
			instruction = ic.option_selected(input);
		
		if(instruction.equals("error")) // if wrong input
			status = false;
		else 
			status = true;
	
	
	}
	
	System.out.println("Enter a file path:");
	status = false ; 
	
	/*
	 * Checking if file path valid
	 * if not the user will be asked to enter again
	 */
	while(!status) // checking existence of the file
	{
		path = in.nextLine();
		
		file.setFile(new File(path));
		if((data = file.ReadBytes()) == null)
			break;
		
		file_content = 	ic.file_path(path);
		
		if(!file_content.equals("error"))
		{
			status = true ; 	
			/*
			 * File encryption
			 */
			if(instruction.equals("encryption"))
			{
				System.out.println("Your encryption key is: " + (int)cc.createKey());
				cc.setInput(data);
				
				// writing the encrypted bytes to file.encrypted
				if(file.writeBytesToFile(file.getFile().getPath() +".encrypted", cc.encrypt()) )
				{
					System.out.println("File has been encrypted successfully!");
					System.out.println("File saved at: " +file.getFilePathNoType()+".encrypted");
				}
				
				else
					System.out.println("Encryption failed!");
				
			}
			/*
			 * File decryption
			 */
			else if(instruction.equals("decryption"))
			{
				System.out.println("Enter key:");
				instruction = in.nextLine();
				cc.setKey((byte) ic.to_integer(instruction));
				cc.setInput(data);
				
				if(file.writeStringTofile(file.getFilePathNoType()+"_decrypted"+"."+file.getFile().getPath().split("\\.")[1], cc.decrypt()))
						{
							System.out.println("File has been decrypted successfully!");
							System.out.println("File saved at: " +file.getFile().getPath().split("\\.")[0]+"_decrypted"+"."+file.getFile().getPath().split("\\.")[1]);
						}
				else
					System.out.println("Decryption failed!");
			}
		}
		
		else
		{
			status = false ;
			System.out.println("Invalid path ,please enter a valid one");
			
		}
	}
	
	in.close();
		
}



	
}


