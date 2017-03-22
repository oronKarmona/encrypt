package MultiFiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import jaxb.JAXBCon;
import jaxb.StatusJaxb;

import org.apache.log4j.Logger;

import com.encrypt.EnumCipher;
import com.encrypt.ErrorMSG;
import com.encrypt.FileManager;
import com.encrypt.Main;
import com.encrypt.Ciphers.AbstractDouble;
import com.encrypt.Ciphers.Cipher;

public class AsyncThread extends Thread{

	File file ; 
	Cipher alg ; 
	ArrayList<File> listOfFiles;
	String target;
	FileManager fm ; 
	EnumCipher action;
	File folder ;
	final Logger log = Logger.getLogger(AsyncThread.class);
	
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
							System.out.println( "Reading file has failed");
							e.printStackTrace();
							String st = org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e);
							ErrorMSG.addEx("Reading file has failed",st);
							log.error(ErrorMSG.msg);
							log.error(ErrorMSG.ecxeption);

							try {
								JAXBCon.appendXml(new StatusJaxb("Failed",file.getPath(),"Reading File",e.getClass().getName(),e.getMessage()+"",st));
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						alg.setFilePath(file.getPath());
						if(alg instanceof AbstractDouble)
							((AbstractDouble)alg).setScondaryCipherFilePath();
						alg.setInput(data);
						// if this is an Encryption action
						if(action.equals(EnumCipher.Encryption))
						{
							try {
								alg.start("Encryption");
								data = alg.encrypt();
							} catch (Exception e) {
								System.out.println( "Encryption has failed");
								e.printStackTrace();
								String st = org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e);

								ErrorMSG.addEx("Encryption has failed",st);
								log.error(ErrorMSG.msg);
								log.error(ErrorMSG.ecxeption);
								
								try {
									JAXBCon.appendXml(new StatusJaxb("Failed",file.getPath(),"Encryption",e.getClass().getName(),e.getMessage()+"",st));
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}
							
							fm.writeBytesToFile(target+"\\"+file.getName()+".encrypted", data);
							alg.End("Encryption");
						}
						// if this is a Decryption action
						else if(action.equals(EnumCipher.Decryption))
						{
							try {
								alg.start("Decryption");
								alg.decrypt();
								data = alg.getOutput();
							} catch (Exception e) {
								String st = org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e);
								System.out.println( "Decryption has failed");
								e.printStackTrace();
								ErrorMSG.addEx("Decryption has failed",e.getMessage());
								log.error(ErrorMSG.msg);
								log.error(ErrorMSG.ecxeption);
								
								try {
									JAXBCon.appendXml(new StatusJaxb("Failed",file.getPath(),"Decryption",e.getClass().getName(),e.getMessage()+"",st));
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}
							fm.setFile(file);
							alg.End("Decryption");
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
