package MultiFiles;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;

import com.encrypt.EnumCipher;
import com.encrypt.FileManager;
import com.encrypt.Main;
import com.encrypt.Ciphers.AbstractDouble;
import com.encrypt.Ciphers.Cipher;

public class Async {
	
	public static Cipher c ; 
	public static File folder;
	public static EnumCipher option;
	public static ArrayList<File> listOfFiles;
	public static ArrayList<AsyncThread> at;
	public static volatile  int  sync ;
	final static Logger log = Logger.getLogger(Async.class);

	public static void start() throws CloneNotSupportedException
	{
		long start_time = System.nanoTime();
		log.info("Async operation has started");
		at = new ArrayList<AsyncThread>();
		listOfFiles = new ArrayList<File>();
		
		for(File file : folder.listFiles())
			if (file.isFile())
				listOfFiles.add(file);
		
		int max = 0 ;
		
		if(listOfFiles.isEmpty())
		{
			System.out.println("This folder is empty! no files to manipulate");
			return;
		}
		
		
		else if(listOfFiles.size() == 1 )
			max = 1 ; 
		
		else if(listOfFiles.size() == 2)
			max = 2;
		
		else if(listOfFiles.size() == 3)
			max = 3;
		
		else // 4 or more files
			max = 4;
		
		String target = null; 
		sync = max;
		if (option.equals(EnumCipher.Encryption))
		{
			new File(folder.getAbsolutePath()+"\\encrypted").mkdir();
			target = folder.getAbsolutePath()+"\\encrypted";
			
		}
		
		if (option.equals(EnumCipher.Decryption))
		{
			new File(folder.getParent()+"\\decrypted").mkdir();
			target = folder.getParent()+"\\decrypted";

		}
		
		for(int i = 0 ; i < max; i++)
		{
			Cipher temp = (Cipher) c.clone();
			if(c instanceof AbstractDouble)
			{	
				ArrayList<Cipher> c_ciphers = new ArrayList<Cipher>();
				ArrayList<Cipher> list = ((AbstractDouble)temp).getCiphers();
				for(Cipher cp : list)
				{
					c_ciphers.add(((Cipher)cp.clone()));
				}
				
				((AbstractDouble)temp).setCiphers(c_ciphers);
			}
			at.add(new AsyncThread(listOfFiles.remove(i-i),temp,target , option ,folder ));
		}
		
		for(AsyncThread t : at)
			t.start();
		
		if (option.equals(EnumCipher.Encryption))
			new FileManager().writeBytesToFile(folder.getAbsolutePath()+"\\key.bin", c.getByteArrayKey());
		
		while(sync > 0);
		
		long total_time = System.nanoTime() - start_time;
		log.info("Async operation has Finished - Total time: " + ((total_time)/(double)1000000) +" ms");
		System.out.println(" Finished - Total time: " + ((total_time)/(double)1000000) +" ms");
	}
	
	public static synchronized void downSync()
	{
		sync--;
	}
	
	public static synchronized File checkLeft()
	{
		if(listOfFiles.isEmpty())
			return null;
		
		return listOfFiles.remove(0);
	}

}
