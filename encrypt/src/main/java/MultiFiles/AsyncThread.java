package MultiFiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import com.encrypt.EnumCipher;
import com.encrypt.FileManager;
import com.encrypt.Ciphers.Cipher;

public class AsyncThread extends Thread{

	File file ; 
	Cipher alg ; 
	ArrayList<File> listOfFiles;
	String target;
	FileManager fm ; 
	EnumCipher action;
	File folder ;
	
	
	public AsyncThread(File file , Cipher alg, String target , EnumCipher action ,File folder)
	{
		this.file = file ; 
		this.alg  =  alg; 
		this.target = target;
		fm = new FileManager();
		this.action = action;
		this.folder = folder;

		
	
	}



	@Override
	public void run() 
	{
		boolean again = true;
		
		while(again)
		{
					byte[] data = null;
		
						try {
							data = Files.readAllBytes(file.toPath());
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						alg.setInput(data);
						// if this is an Encryption action
						if(action.equals(EnumCipher.Encryption))
						{
							try {
								data = alg.encrypt();
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							fm.writeBytesToFile(target+"\\"+file.getName()+".encrypted", data);
						}
						// if this is a Decryption action
						else if(action.equals(EnumCipher.Decryption))
						{
							try {
								alg.decrypt();
								data = alg.getOutput();
							} catch (Exception e) {
								e.printStackTrace();
							}
							fm.setFile(file);
						
							String n = folder.getParent()+"\\decrypted\\"+file.getName().split("\\.")[0]+"_decrypted"+"."+file.getPath().split("\\.")[1];
							fm.writeBytesToFile(n, data);
						}
						
				// if there are waiting files	
				if((file = Async.checkLeft()) == null)
					again = false;
					
					
		}
		
		Async.downSync();
		
	}
}
