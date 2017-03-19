package com.encrypt;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import lombok.Getter;
import lombok.Setter;

/***
 * This class will be used a file manager for a specific file
 * @author Oron
 *
 */
public class FileManager {
	/***
	 * The specific file this class manages
	 */
	@Getter @Setter private File file;
	
	/***
	 * Cuts only the simple file path from the absolute path of the file.
	 * @return path of the file 
	 */
	public String getOnlyPath()
	{
		   String filePath = file.getAbsolutePath().substring(0,file.getAbsolutePath().lastIndexOf(File.separator));
		   return filePath;
	}
	/***
	 * Return the file's name
	 * @return String of the file name
	 */
	public String getFilename()
	{
		 return this.file.getName().split("\\.")[0];
	}
	
	/***
	 * Reading the bytes of the files
	 * @return bytes[] read from the file
	 * @return null for reading error
	 */
	public byte[] ReadBytes(String path)
	{
		byte data[] = null;
		int i = 0 ; 
		
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(path);

			System.out.println("Total file size to read (in bytes) : " + fis.available());
			data = new byte[fis.available()];
			int content;
			while ((content =  fis.read()) != -1) 
			{
				data[i] = (byte)content;
				i++;
			}

		} catch (IOException e) {
			System.out.println("Faile to read file "+ path);
			e.printStackTrace();
			ErrorMSG.addEx("Faile to read file "+ path, e.getMessage());
		} finally {
			try {
				if (fis != null)
				{
					fis.close();
					return data;
				}
			} catch (IOException ex) {
				
				System.out.println("Failed to finish reading file "+ path);
				ex.printStackTrace();
				ErrorMSG.addEx("Failed to finish reading file "+ path, ex.getMessage());
				return null;
			}
		}
		return data;
		
		
	}
	
	/***
	 * Getting the name of the file without its format
	 * "file.txt" -> "file"
	 * @return file's name
	 */
	public String getFilePathNoType()
	{
		return this.file.getPath().split("\\.")[0];
	}
	
	/***
	 * This method writes bytes to file in certain path with its name
	 * @param path - path of the file with its name
	 * @param content - bytes to be written
	 * @return true for success , false for failure
	 */
	public boolean writeBytesToFile(String path,byte[] content)
	{
		try {
			FileOutputStream fos = new FileOutputStream(path);
			System.out.println(content.length);
			//for(byte c : content)
				fos.write(content);
			fos.close();
			return true;
		} 
		catch (Exception e) {
			
			System.out.println("Failed to write bytes to "+ path);
			e.printStackTrace();
			ErrorMSG.addEx("Failed to write bytes to "+ path, e.getMessage());
			
			return false;
		}
		
	}
	
	/***
	 * This method is writing string to  file in certain path with its name
	 * @param path - path of the file with its name
	 * @param content - String to be written
	 * @return true for success , false for failure
	 */
	public boolean writeStringTofile(String path,String content)
	{
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(new File(path)));
			output.write(content);
			output.close();
			return true;
			
		} catch (Exception e) {
			System.out.println("Failed to write file");
			e.printStackTrace();
			return false;
		}
	}
	
	

	
	


}
