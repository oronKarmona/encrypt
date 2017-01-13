package com.encrypt;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

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
	 * creating the mock variable before starting the testing
	 */
	@Before
	public void create()
	{
		cc = mock(CaesarCipher.class);
	}
	
	/***
	 * Checking if the created key is set as the key
	 */
	@Test
	public void createKeyTest() 
	{
		cc = new CaesarCipher();
		byte result = cc.createKey();
		//expected to be minimalValue <= result <= maximalValue
		assertTrue(result >= cc.getMinimalValue() && result <= cc.getMaximalValue());
		
	}
	/***
	 * Checking if the encrypted bytes are encrypted ok
	 */
	@Test
	public void checkEncryptionTest()
	{
		CaesarCipher cc2 = new CaesarCipher();
		byte[] input = {112,100};
		cc2.setInput(input);
		cc2.setKey((byte)5);
		byte[] result = cc2.encrypt();
		byte[] expected = {117,105};
		
		assertEquals(new ByteArrayComp().compare(result, expected),1);
	}
	
	/***
	 * Checking if the encrypted bytes are encrypted ok even if the input + key bigger than maximal value
	 */
	@Test
	public void checkEncryption_biggerThanMaxTest()
	{
		CaesarCipher cc2 = new CaesarCipher();
		byte[] input = {112,100};
		cc2.setInput(input);
		cc2.setKey((byte)100);
		byte[] result = cc2.encrypt();
		byte[] expected = {-43,-55};
		
		assertEquals(new ByteArrayComp().compare(result, expected),1);
	}
	
	/***
	 * Checking if the decrypted bytes are decrypted ok
	 */
	@Test
	public void checkDecryptionTest()
	{
		CaesarCipher cc2 = new CaesarCipher();
		byte[] input = {112,100};
		cc2.setInput(input);
		cc2.setKey((byte)100);
		String result = cc2.decrypt();
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
		CaesarCipher cc2 = new CaesarCipher();
		byte[] input = {-126,-125};
		cc2.setInput(input);
		cc2.setKey((byte)cc2.getMaximalValue());
		String result = cc2.decrypt();
		byte[] e = {12,0};
		String expected = new String(e);
		
		assertTrue(expected.equals(result));
	}
	
	

}
