package com.encrypt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

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
	 */
	public byte[] ReadBytes()
	{
		byte data[] ;
		try {
			 data = Files.readAllBytes(file.toPath());
			 return data;
			} 
		catch (IOException e) {
			System.out.println("Failed to read file");
			e.printStackTrace();
			return null;
		}
		
		
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
			fos.write(content);
			fos.close();
			return true;
		} 
		catch (Exception e) {
			System.out.println("Failed to write file");
			e.printStackTrace();
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
