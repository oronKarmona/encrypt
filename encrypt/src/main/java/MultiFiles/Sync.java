package MultiFiles;

import java.io.File;

import jaxb.JAXBCon;
import jaxb.StatusJaxb;

import org.apache.log4j.Logger;

import com.encrypt.EnumCipher;
import com.encrypt.FileManager;
import com.encrypt.Main;
import com.encrypt.Ciphers.Cipher;

/***
 * This class contains static methods for accessing from the main class
 * @author Oron
 *
 */
public class Sync {
	final static Logger log = Logger.getLogger(Sync.class);
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
		String operation = "";
		if (option.equals(EnumCipher.Encryption))
		{
			new File(folder.getAbsolutePath()+"\\encrypted").mkdir();
			target = folder.getAbsolutePath()+"\\encrypted";
			action = EnumCipher.Encryption;
			operation = "Encryption";
		}
		
		if (option.equals(EnumCipher.Decryption))
		{
			new File(folder.getParent()+"\\decrypted").mkdir();
			target = folder.getParent()+"\\decrypted";
			action = EnumCipher.Decryption;
			operation = "Decryption";
		}
		
		SyncThread te = new SyncThread(folder , c , target,action);
		
		log.info("Sync thread has started");
		
			te.start();
		
		
		if (option.equals(EnumCipher.Encryption))
			new FileManager().writeBytesToFile(folder.getAbsolutePath()+"\\key.bin", c.getByteArrayKey());
	
		
		while(te.isAlive());
		
		long total_time = System.nanoTime() - start_time;
		log.info("Sync thread has finished- Total time: " + ((total_time)/(double)1000000) +" ms");
		System.out.println(" Finished - Total time: " + ((total_time)/(double)1000000) +" ms");
		
		try {
			JAXBCon.appendXml(new StatusJaxb("Success",target,((total_time)/(double)1000000) +" ms",operation));
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
	}

}
