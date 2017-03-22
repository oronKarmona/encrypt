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

public class SyncThread extends Thread{
	
	File folder ; 
	Cipher alg ; 
	ArrayList<File> listOfFiles;
	String target;
	FileManager fm ; 
	EnumCipher action;
	final Logger log = Logger.getLogger(SyncThread.class);
	public SyncThread(File folder , Cipher alg, String target , EnumCipher action)
	{
		this.folder = folder ; 
		this.alg  = alg; 
		this.target = target;
		fm = new FileManager();
		this.action = action;
		listOfFiles = new ArrayList<File>();
		
		for(File file : folder.listFiles())
		{
			if (file.isFile())
			{
				listOfFiles.add(file);
			}
			
		}
	}



	@Override
	public void run() 
	{
		byte[] data = null; 
		
		for(int i = 0 ; i < listOfFiles.size() ; i++)
		{
			System.out.println("-------"+ (i+1)+ "-------");
						try {
							data = Files.readAllBytes(listOfFiles.get(i).toPath());
						} catch (IOException e) {
							System.out.println( "Reading file has failed");
							e.printStackTrace();
							String st = org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e);
							ErrorMSG.addEx("Reading file has failed",st);
							log.error("Reading file has failed");
							log.error(e.getMessage());
							
							try {
								JAXBCon.appendXml(new StatusJaxb("Failed",target+"\\"+listOfFiles.get(i).getName(),"Encryption",e.getClass().getName(),e.getMessage()+"",st));
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						
						alg.setFilePath(listOfFiles.get(i).getPath());
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
								String st = org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e);
								System.out.println( "Encryption failed");
								e.printStackTrace();
								ErrorMSG.addEx("Encryption failed",st);
								log.error(ErrorMSG.msg);
								log.error(ErrorMSG.ecxeption);
								try {
									JAXBCon.appendXml(new StatusJaxb("Failed",target+"\\"+listOfFiles.get(i).getName(),"Encryption",e.getClass().getName(),e.getMessage()+"",st));
								} catch (Exception e1) {
									e1.printStackTrace();
								}
								
							}
							
							fm.writeBytesToFile(target+"\\"+listOfFiles.get(i).getName()+".encrypted", data);
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

								System.out.println( "Decryption failed");
								e.printStackTrace();
								ErrorMSG.addEx("Decryption failed",st);
								log.error(ErrorMSG.msg);
								log.error(ErrorMSG.ecxeption);
								
								try {
									JAXBCon.appendXml(new StatusJaxb("Failed",target+"\\"+listOfFiles.get(i).getName(),"Decryption",e.getClass().getName(),e.getMessage()+"",st));
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}
							fm.setFile(listOfFiles.get(i));
							String n = folder.getParent()+"\\decrypted\\"+listOfFiles.get(i).getName().split("\\.")[0]+"_decrypted"+"."+listOfFiles.get(i).getPath().split("\\.")[1];
							fm.writeBytesToFile(n, data);
							alg.End("Decryption");
						}
		}
		
		System.out.println("All files are saved at "+target);
		
	}

}
