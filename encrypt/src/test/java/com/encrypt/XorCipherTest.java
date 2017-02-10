package com.encrypt;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.encrypt.Ciphers.XorCipher;

public class XorCipherTest {

	private XorCipher xc;
	
	
	/***
	 * Checking that the encryption is done correctly
	 */
	@Test
	public void encryptiontest() {
		xc = new XorCipher();
		byte input[] = {112,100};
		xc.setInput(input);
		xc.setKey((byte) 5);
		
		xc.encrypt();
		byte result[] = xc.getInput();
		byte expected[] = {117,97};
		
		assertEquals(new ByteArrayComp().compare(result, expected),1);
		
	}
	
	/***
	 * Checking that the decryption is done correctly
	 */
	@Test
	public void decryptiontest() {
		
		xc = new XorCipher();
		byte input[] = {117,97};
		xc.setInput(input);
		xc.setKey((byte) 5);
		
		xc.decrypt();
		byte result[] = xc.getOutput();
		byte expected[] = {112,100};
		
		assertEquals(new ByteArrayComp().compare(result, expected),1);
		
	}

}
