package jaxb;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;

import org.apache.log4j.Logger;

import com.encrypt.Main;
import com.encrypt.Ciphers.AbstractDouble;
import com.encrypt.Ciphers.CaesarCipher;
import com.encrypt.Ciphers.Cipher;
import com.encrypt.Ciphers.DoubleCipher;
import com.encrypt.Ciphers.MWOCipher;
import com.encrypt.Ciphers.ReverseCipher;
import com.encrypt.Ciphers.SplitCipher;
import com.encrypt.Ciphers.XorCipher;
/***
 * This class has static methods to comunicate with the XML configuration file
 * @author Oron
 *
 */
public class DataJAXB 
{
	
		public static void marshall(Object obj)
		{
			final Logger log = Logger.getLogger(DataJAXB.class);
			
			
			try {
				JAXBContext jc = JAXBContext.newInstance(Data.class);
				Marshaller ms = jc.createMarshaller();
				ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				ms.marshal(obj, System.out);
				ms.marshal(obj, new File("Config.xml"));
				log.info("Config.xml has been updated Succesfully");
				
				
			} catch (JAXBException e) {
				
				e.printStackTrace();
				log.error("Config.xml  update has failed");
				log.error(e.getMessage());
			}
		}
		
		
		public static Cipher unmarshall()
		{
			final Logger log = Logger.getLogger(DataJAXB.class);
			String cipher_name = null;
			String second_cipher_name_1 = null;
			String second_cipher_name_2 = null;
			try {
				JAXBContext jc = JAXBContext.newInstance(Data.class);
				Unmarshaller ums = jc.createUnmarshaller();
				Data data = (Data)ums.unmarshal(new File("Config.xml"));
				
				 cipher_name = data.getM_cipher();
				 second_cipher_name_1 = data.getS_cipher1();
				 second_cipher_name_2 = data.getS_cipher2();
				
				System.out.println("The default Cipher is:");
				System.out.println(data.getM_cipher());
				log.info("The default Cipher is:" + cipher_name);
				
				if(second_cipher_name_1 != null)
				{
				System.out.println("Secondary Cipher 1 "+data.getS_cipher1());
				log.info("Secondary Cipher 1 "+data.getS_cipher1());
				}
				if(second_cipher_name_2 != null)
				{
				System.out.println("Secondary Cipher 2 "+data.getS_cipher2());
				log.info("Secondary Cipher 2 "+data.getS_cipher2());
				}
				
				log.info("Config.xml has been read succesfully");
				
			
			} 
			catch (JAXBException e) 
			{
				log.error("Config.xml reading has failed");
				log.error(e.getMessage());
			}
			
			Cipher c = DataJAXB.fromString2Cipher(cipher_name);
			
			if(c != null)
				return c ;
			
			return DataJAXB.fromString2DoubleCipher(cipher_name, second_cipher_name_1, second_cipher_name_2);
		}
		
		/***
		 * Convert string to regular cipher object
		 * @param cipher_name - cipher to be converted to 
		 * @return - the cipher object
		 */
		public static Cipher fromString2Cipher(String cipher_name)
		{
			if(cipher_name.equals("CaesarCipher"))
				return new CaesarCipher();
			
			else if(cipher_name.equals("XorCipher"))
					return new XorCipher();
			
			else if(cipher_name.equals("MWOCipher"))
				return new MWOCipher();
			
			else return null;
			
		}
		
		/***
		 * Convert String to Double Cipher
		 * @param cipher_name - Main cipher name
		 * @param second_cipher_name_1 - secondary cipher name 
		 * @param second_cipher_name_2 - secondary cipher name
		 * @return - the double cipher with its seconderies ciphers
		 */
		public static Cipher fromString2DoubleCipher(String cipher_name,String second_cipher_name_1 ,String second_cipher_name_2)
		{
			if(cipher_name.equals("DoubleCipher"))
			{
				return new DoubleCipher(fromString2Cipher(second_cipher_name_1),fromString2Cipher(second_cipher_name_2));
			}
			
			else if(cipher_name.equals("SplitCipher"))
			{
				return new SplitCipher(fromString2Cipher(second_cipher_name_1),fromString2Cipher(second_cipher_name_1));
			}
			
			else if(cipher_name.equals("ReverseCipher"))
			{
				return new ReverseCipher(fromString2Cipher(second_cipher_name_1),null);
			}
			
			else return null;
				
		}
		
		/***
		 * Converting CIpher object to string
		 * @param cipher - object to be converted
		 * @return - the string the object is converted to 
		 */
		public static String fromCipher2String(Cipher cipher)
		{
			if(cipher instanceof CaesarCipher)
				return "CaesarCipher";
			else if(cipher instanceof XorCipher)
				return "XorCipher";
			else if(cipher instanceof MWOCipher)
				return "MWOCipher";
			else return null;
		}
		
		/***
		 * marshaling Cipher
		 * @param cipher - double cipher to be marshalled
		 */
		public static void MarshallCipher(Cipher cipher)
		{
			ArrayList<Cipher> c  = null;
			
			if(cipher instanceof AbstractDouble)
				 c = ((AbstractDouble)cipher).getCiphers();
			
			if(cipher instanceof DoubleCipher)
			{
				DataJAXB.marshall(new Data("DoubleCipher",DataJAXB.fromCipher2String(c.get(0)),DataJAXB.fromCipher2String(c.get(1))));
			}
			
			else if(cipher instanceof SplitCipher)
			{
				DataJAXB.marshall(new Data("SplitCipher",DataJAXB.fromCipher2String(c.get(0)),null));
			}
			
			else if(cipher instanceof ReverseCipher)
			{
				DataJAXB.marshall(new Data("ReverseCipher",DataJAXB.fromCipher2String(c.get(0)),null));
			}
			
			else
				DataJAXB.marshall(new Data(DataJAXB.fromCipher2String(cipher),null,null));
			
		}
		
}
