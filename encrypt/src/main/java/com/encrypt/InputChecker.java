package com.encrypt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import lombok.Data;

/***
 * This class is used to check user input 
 * @author Oron
 *
 */
@Data public class InputChecker {
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
		}catch(Exception e)
		{
			return -1;
		}
	}
/***
 * Reads the file content
 * @param path - the path of the file
 * @return file content
 */
public String file_path(String path)
	{
		String sCurrentLine ;
		String temp = "";
		try(BufferedReader br = new BufferedReader(new FileReader(path)))
		{	
			while((sCurrentLine = br.readLine() )!= null )
			{
				temp += sCurrentLine;
			}
			
			return temp;
		} 
		catch(IOException e)
		{
			return "error";
		}
	}
	/***
	 * Returning an action due to the user input
	 * @param input - user selection
	 * @return the action to be executed
	 */
	public  String option_selected(int input)
	{
		switch(input)
		{
		case 1:
			return "encryption";
		case 2:
			return "decryption";
		default:
			return "error";
		}
	}

}
