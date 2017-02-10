package com.encrypt;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.encrypt.Ciphers.MWOCipher;
import com.encrypt.Ciphers.XorCipher;

public class MWOCipherTest {

	private MWOCipher mw;
	
	
	/***
	 * Checking that the encryption is done correctly
	 */
	@Test
	public void encryptiontest() {
		 mw = new MWOCipher();
		byte input[] = {112,100};
		mw.setInput(input);
		mw.setKey((byte) 5);
		
		mw.encrypt();
		byte result[] = mw.getInput();
		byte expected[] = {(byte)(5*112),(byte)(5*100)};
		
		assertEquals(new ByteArrayComp().compare(result, expected),1);
		
	}
	
	/***
	 * Checking that the decryption is done correctly with valid key
	 */
	@Test
	public void decryptionValidKeytest() {
		
		 mw = new MWOCipher();
		byte input[] = {48,-12};
		mw.setInput(input);
		mw.setKey((byte) 5);
		
		try {
			mw.decrypt();
		} catch (Exception e) {
			//error
			assertFalse(true);
		}
		byte result[] = mw.getOutput();
		byte expected[] = {112,100};
		
		assertEquals(new ByteArrayComp().compare(result, expected),1);
		
	}
	
	/***
	 * Checking that the decryption is done correctly with invalid key
	 */
	@Test
	public void decryptionInvalidKeytest() {
		
		 mw = new MWOCipher();
		byte input[] = {48,-12};
		mw.setInput(input);
		mw.setKey((byte) 2);
		boolean result = true;
		try {
			mw.decrypt();
			
		} catch (Exception e) {
			//error
			result = false;
		}
		
		assertFalse(result);
		
	}

}
