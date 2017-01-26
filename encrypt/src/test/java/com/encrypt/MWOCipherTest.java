package com.encrypt;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.encrypt.Ciphers.MWOCipher;
import com.encrypt.Ciphers.XorCipher;

public class MWOCipherTest {

	/***
	 * Mocked object
	 */
	private MWOCipher mw;
	
	/***
	 * creating the mock variable before starting the testing
	 */
	@Before
	public void create()
	{
		mw = mock(MWOCipher.class);
	}
	
	/***
	 * Checking that the encryption is done correctly
	 */
	@Test
	public void encryptiontest() {
		MWOCipher mw2 = new MWOCipher();
		byte input[] = {112,100};
		mw2.setInput(input);
		mw2.setKey((byte) 5);
		
		mw2.encrypt();
		byte result[] = mw2.getInput();
		byte expected[] = {(byte)(5*112),(byte)(5*100)};
		
		assertEquals(new ByteArrayComp().compare(result, expected),1);
		
	}
	
	/***
	 * Checking that the decryption is done correctly with valid key
	 */
	@Test
	public void decryptionValidKeytest() {
		
		MWOCipher mw2 = new MWOCipher();
		byte input[] = {48,-12};
		mw2.setInput(input);
		mw2.setKey((byte) 5);
		
		try {
			mw2.decrypt();
		} catch (Exception e) {
			//error
			assertFalse(true);
		}
		byte result[] = mw2.getOutput();
		byte expected[] = {112,100};
		
		assertEquals(new ByteArrayComp().compare(result, expected),1);
		
	}
	
	/***
	 * Checking that the decryption is done correctly with invalid key
	 */
	@Test
	public void decryptionInvalidKeytest() {
		
		MWOCipher mw2 = new MWOCipher();
		byte input[] = {48,-12};
		mw2.setInput(input);
		mw2.setKey((byte) 2);
		boolean result = true;
		try {
			mw2.decrypt();
			
		} catch (Exception e) {
			//error
			result = false;
		}
		
		assertFalse(result);
		
	}

}
