package MultiFiles;

import java.io.File;

import com.encrypt.EnumCipher;
import com.encrypt.FileManager;
import com.encrypt.Ciphers.Cipher;

/***
 * This class contains static methods for accessing from the main class
 * @author Oron
 *
 */
public class Sync {
	
	public static Cipher c ; 
	public static File folder;
	public static EnumCipher option;
	
	/***
	 * Start the sync process
	 */
	public static void action()
	{
		String target = null;
		EnumCipher action = null;
		long start_time = System.nanoTime();
		if (option.equals(EnumCipher.Encryption))
		{
			new File(folder.getAbsolutePath()+"\\encrypted").mkdir();
			target = folder.getAbsolutePath()+"\\encrypted";
			action = EnumCipher.Encryption;
		}
		
		if (option.equals(EnumCipher.Decryption))
		{
			new File(folder.getParent()+"\\decrypted").mkdir();
			target = folder.getParent()+"\\decrypted";
			action = EnumCipher.Decryption;
		}
		
		SyncThread te = new SyncThread(folder , c , target,action);
		
		
			te.start();
		
		
		if (option.equals(EnumCipher.Encryption))
			new FileManager().writeBytesToFile(folder.getAbsolutePath()+"\\key.bin", c.getByteArrayKey());
	
		
		while(te.isAlive());
		
		long total_time = System.nanoTime() - start_time;
		System.out.println(" Finished - Total time: " + ((total_time)/(double)1000000) +" ms");
		
	}

}
