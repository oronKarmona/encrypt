package com.encrypt;



import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.encrypt.Ciphers.CaesarCipher;
import com.encrypt.Ciphers.ReverseCipher;

/***
 * Testing the reverse cipher algorithm
 * @author Oron
 *
 */
public class ReverseCipherTest {

	
	ReverseCipher rc ; 
	
	/***
	 * Testing encryption with caesarcipher decryption
	 */
	@Test
	public void encryptionTest()
	{
		byte[] input = {112,100};
		byte[] expected = {12,0};
		byte[] result = null;
		CaesarCipher cc = new CaesarCipher();
		cc.setKey((byte)100);
		rc = new ReverseCipher(cc,null);
		rc.setInput(input);
		try {
			result = rc.encrypt();
			
		} catch (Exception e) {
			
			assertFalse(false);
			e.printStackTrace();
		}
		
		assertEquals(new ByteArrayComp().compare(result, expected),1);
		
	}
	
	/***
	 * Testing decryption with caesarcipher encryption
	 */
	@Test
	public void decryptionTest()
	{
		byte[] input = {12,0};
		byte[] expected = {112,100};
		ArrayList<Byte> keys = new ArrayList<Byte>();
		keys.add((byte)100);
		String result = null;
		CaesarCipher cc = new CaesarCipher();
		
		rc = new ReverseCipher(cc,null);
		rc.setKeys(keys);
		rc.setInput(input);
		try {
			result = rc.decrypt();
			
		} catch (Exception e) {
			
			assertFalse(false);
			e.printStackTrace();
		}
		
		assertTrue(result.equals(new String(expected)));
	}

}
