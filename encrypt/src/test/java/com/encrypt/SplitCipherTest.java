package com.encrypt;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.encrypt.Ciphers.SplitCipher;
import com.encrypt.Ciphers.XorCipher;

/***
 * Testing the splitCipher
 * @author Oron
 *
 */
public class SplitCipherTest {

	private ArrayList<Byte> keys;
	
	/***
	 * Initializing the keys for both decryption and encryption
	 */
	@Before
	public void before()
	{
		keys = new ArrayList<Byte>(); // keys
		keys.add((byte)100);
		keys.add((byte)20);
	}
	
	/***
	 * Testing the encryption by comparing the result of encryption on even and odd indexes to the 
	 * split cipher encryption result
	 */
	@Test
	public void encryptionTest() 
	{
		byte[] input = {(byte)100,(byte)20}; // input data
		byte[] o_expected ,e_expected , expected = new byte[2],result = null;
		
		XorCipher xc = new XorCipher();
		xc.setInput(Arrays.copyOf(input,input.length)); // sending copy of the input array
		xc.setKey(keys.get(1));
		e_expected = xc.encrypt(); // even result
		
		xc.setInput(Arrays.copyOf(input,input.length)); // sending copy of the input array
		xc.setKey(keys.get(0));
		o_expected = xc.encrypt(); // odd result
		
		expected[0] = e_expected[0];  // taking even result
		expected[1] = o_expected[1];  // taking odd result
		
		SplitCipher ss = new SplitCipher(xc,xc);
		ss.setInput(Arrays.copyOf(input,input.length));
		ss.setKeys(keys);
		try {
			result = ss.encrypt();  // split encryption
			
		} catch (Exception e) {
			assertTrue(false);
		}
		
		assertEquals(new ByteArrayComp().compare(result, expected),1);
	}
	
	/***
	 * Testing the decryption by comparing the result of decryption on even and odd indexes to the 
	 * split cipher decryption result
	 */
	@Test
	public void decryptionTest()
	{
		byte[] input = {(byte)100,(byte)20}; // input data
		byte[] o_expected ,e_expected , expected = new byte[2],result = null;
		
		XorCipher xc = new XorCipher();
		xc.setInput(Arrays.copyOf(input,input.length)); // sending copy of the input array
		xc.setKey(keys.get(1));
		xc.decrypt(); // even result
		e_expected  = xc.getOutput();
		
		xc.setInput(Arrays.copyOf(input,input.length)); // sending copy of the input array
		xc.setKey(keys.get(0));
		xc.decrypt();
		o_expected = xc.getOutput(); // odd result
		
		expected[0] = e_expected[0];  // taking even result
		expected[1] = o_expected[1];  // taking odd result
		
		SplitCipher ss = new SplitCipher(xc,xc);
		ss.setInput(Arrays.copyOf(input,input.length));
		ss.setKeys(keys);
		try {
			 ss.decrypt();  // split encryption
			 result = ss.getOutput();
			 
		} catch (Exception e) {
			assertTrue(false);
		}
		
		assertEquals(new ByteArrayComp().compare(result, expected),1);
	}

}
