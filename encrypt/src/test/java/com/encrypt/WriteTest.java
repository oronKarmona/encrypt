package com.encrypt;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
/***
 * This test checks if the read and write operation are correct on simple string
 * @author Oron
 *
 */
public class WriteTest {

	 @Rule
	   public TemporaryFolder tempFolder = new TemporaryFolder();
	   
	   @Test
	   public void WriteStringReadBytesTest() throws IOException {
	     final File tempFile = tempFolder.newFile("tempFile.txt");
	     FileManager fm = new FileManager();
	     String path = tempFile.getPath();
	     
	     fm.writeStringTofile( path, "hello world");
	   
	     final String s = new String(fm.ReadBytes(path));
	   
	     // Verify the content
	    assertEquals("hello world", s);
	      
	   }
	   /***
	    * This test is checking the read and write of bytes
	    * @throws IOException
	    */
	   @Test
	   public void WriteBytesReadBytesTest() throws IOException 
	   {
		   
	     final File tempFile = tempFolder.newFile("tempFile.txt");
	     FileManager fm = new FileManager();
	     String path = tempFile.getPath();
	     byte[] helloWorld = ("hello world").getBytes();
	     fm.writeBytesToFile( path, helloWorld);
	   
	     final byte[] s = fm.ReadBytes(path);
	   
	     // Verify the content
	    assertEquals(new ByteArrayComp().compare(helloWorld, s) , 1);
	      
	     
	   }
	   
	   
}
