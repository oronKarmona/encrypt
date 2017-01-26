package com.encrypt;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.encrypt.Ciphers.XorCipher;

public class XorCipherTest {

	/***
	 * Mocked object
	 */
	private XorCipher xc;
	
	/***
	 * creating the mock variable before starting the testing
	 */
	@Before
	public void create()
	{
		xc = mock(XorCipher.class);
	}
	
	/***
	 * Checking that the encryption is done correctly
	 */
	@Test
	public void encryptiontest() {
		XorCipher xc2 = new XorCipher();
		byte input[] = {112,100};
		xc2.setInput(input);
		xc2.setKey((byte) 5);
		
		xc2.encrypt();
		byte result[] = xc2.getInput();
		byte expected[] = {117,97};
		
		assertEquals(new ByteArrayComp().compare(result, expected),1);
		
	}
	
	/***
	 * Checking that the decryption is done correctly
	 */
	@Test
	public void decryptiontest() {
		
		XorCipher xc2 = new XorCipher();
		byte input[] = {117,97};
		xc2.setInput(input);
		xc2.setKey((byte) 5);
		
		xc2.decrypt();
		byte result[] = xc2.getOutput();
		byte expected[] = {112,100};
		
		assertEquals(new ByteArrayComp().compare(result, expected),1);
		
	}

}
