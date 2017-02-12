package com.encrypt;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.encrypt.Ciphers.CaesarCipher;
import com.encrypt.Ciphers.DoubleCipher;
import com.encrypt.Ciphers.ReverseCipher;
import com.encrypt.Ciphers.XorCipher;

/***
 * Testing the DoubleCipher
 * @author Oron
 *
 */
public class DoubleCipherTest {


  
  	/***
  	 * Encryption is tested by comparing to the individual reuslt of the 
  	 * pair of ciphers included in the double cipher
  	 */
  	@Test
	public void encryptionTest()
	{
  	   DoubleCipher dc ; 
  	   CaesarCipher cc ; 
  	   XorCipher xc;
  	   ArrayList<Byte> keys;
  	   
		byte[] input = {100,20};
		byte[] s_input,expected,result = null;
		
		keys = new ArrayList<Byte>();
		keys.add((byte) 100);
		keys.add((byte)20);
		
		xc = new XorCipher();
		cc = new CaesarCipher();
		dc = new DoubleCipher(cc,xc);
		//first encryption
		cc.setInput(input);
		cc.setKey(keys.get(0));
		s_input = cc.encrypt();
		//second encryption
		xc.setInput(s_input);
		xc.setKey(keys.get(1));
		expected = xc.encrypt();
		
		// double algorithm encryption
		dc.setKeys(keys);
		dc.setInput(input);
		try {
			result = dc.encrypt();
		} catch (Exception e) 
		{
			assertTrue(false);
		}
		
		assertEquals(new ByteArrayComp().compare(result, expected),1);
		
	}
	
  	/***
  	 * Decryption is tested by comparing to the individual reuslt of the 
  	 * pair of ciphers included in the double cipher
  	 */
	@Test
	public void decryptionTest()
	{
		   DoubleCipher dc ; 
		   CaesarCipher cc ; 
		   XorCipher xc;
		   ArrayList<Byte> keys;
		   
		byte[] input = {100,20};
		byte[] s_input;
		String expected,result = null;
		
		keys = new ArrayList<Byte>();
		keys.add((byte) 100);
		keys.add((byte)20);
		xc = new XorCipher();
		cc = new CaesarCipher();
		dc = new DoubleCipher(cc,xc);
		dc.setInput(Arrays.copyOf(input,input.length));
		
		//second cipher decryption
		xc.setInput(input);
		xc.setKey(keys.get(1));
		xc.decrypt();
		s_input = xc.getOutput();
		
		//first cipher decryption
		cc.setInput(s_input);
		cc.setKey(keys.get(0));
		expected = cc.decrypt();
		
		
		// double algorithm encryption
		dc.setKeys(keys);
		
		try {
			result = dc.decrypt();
		} catch (Exception e) 
		{
			assertTrue(false);
		}
		
		assertTrue(result.equals(new String(expected)));
	}

}
