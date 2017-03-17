package MultiFiles;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import com.encrypt.EnumCipher;
import com.encrypt.FileManager;
import com.encrypt.Ciphers.Cipher;

public class Async {
	
	public static Cipher c ; 
	public static File folder;
	public static EnumCipher option;
	public static ArrayList<File> listOfFiles;
	public static ArrayList<AsyncThread> at;
	public static volatile  int  sync ;
	
	public static void start() throws CloneNotSupportedException
	{
		long start_time = System.nanoTime();
		
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
			at.add(new AsyncThread(listOfFiles.remove(i-i),(Cipher)c.clone(),target , option ,folder ));
		
		for(AsyncThread t : at)
			t.start();
		
		if (option.equals(EnumCipher.Encryption))
			new FileManager().writeBytesToFile(folder.getAbsolutePath()+"\\key.bin", c.getByteArrayKey());
		
		while(sync > 0);
		
		long total_time = System.nanoTime() - start_time;
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
