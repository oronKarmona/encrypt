package com.encrypt;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import MultiFiles.Cipher;

import com.encrypt.Ciphers.CaesarCipher;
import com.encrypt.Ciphers.XorCipher;

/***
 * Testing CaesarCipher methods
 * @author Oron
 *
 */
public class CaesarCipherTest {
	/***
	 * This attribute used as mocked object 
	 */
	private CaesarCipher cc ; 
	
	
	/***
	 * Checking if the created key is valid
	 */
	@Test
	public void createKeyTest() 
	{
		cc = new CaesarCipher();
		cc.createKey();
		byte result = cc.getKey();
		//expected to be minimalValue <= result <= maximalValue
		assertTrue(result >= cc.getMinimalValue() && result <= cc.getMaximalValue());
		
	}
	/***
	 * Checking if the encrypted bytes are encrypted ok
	 */
	@Test
	public void checkEncryptionTest()
	{
		 cc = new CaesarCipher();
		byte[] input = {112,100};
		cc.setInput(input);
		cc.setKey((byte)5);
		byte[] result = cc.encrypt();
		byte[] expected = {117,105};
		
		assertEquals(new ByteArrayComp().compare(result, expected),1);
	}
	
	/***
	 * Checking if the encrypted bytes are encrypted ok even if the input + key bigger than maximal value
	 */
	@Test
	public void checkEncryption_biggerThanMaxTest()
	{
		 cc = new CaesarCipher();
		byte[] input = {112,100};
		cc.setInput(input);
		cc.setKey((byte)100);
		byte[] result = cc.encrypt();
		byte[] expected = {-43,-55};
		
		assertEquals(new ByteArrayComp().compare(result, expected),1);
	}
	
	/***
	 * Checking if the decrypted bytes are decrypted ok
	 */
	@Test
	public void checkDecryptionTest()
	{
		 cc = new CaesarCipher();
		byte[] input = {112,100};
		cc.setInput(input);
		cc.setKey((byte)100);
		String result = cc.decrypt();
		byte[] e = {12,0};
		String expected = new String(e);
		
		assertTrue(expected.equals(result));
	}
	
	/***
	 * Checking if the decrypted bytes are decrypted ok even if the output - key < minimalValue
	 */
	@Test
	public void checkDecryption_smallerThanMinTest()
	{
		 cc = new CaesarCipher();
		byte[] input = {5,3};
		cc.setInput(input);
		cc.setKey((byte)cc.getMaximalValue());
		cc.setInput(cc.encrypt());
		String result = cc.decrypt();
		byte[] e = {5,3};
		String expected = new String(e);
		
		assertTrue(expected.equals(result));
	}
	
	
	

}
