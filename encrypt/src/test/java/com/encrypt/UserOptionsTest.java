package com.encrypt;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import MultiFiles.Cipher;

import com.encrypt.Ciphers.CaesarCipher;
/***
 * This testing class will test the UserOptions class
 * @author Oron
 *
 */
public class UserOptionsTest {
	
	UserOptions uo ; 
	
	@Before
	public void create()
	{
		uo = mock(UserOptions.class);
	}
	

	
	
	/***
	 * Test for invalid option selected by the user
	 * Expected result should be "error"
	 */
	@Test
	public void valid_option_selectionTest() {
		uo = new UserOptions();
		Cipher result = uo.algorithms_menu(1);
		
		assertTrue(result instanceof CaesarCipher);	
	}
	

	/***
	 * Test for valid change string to int
	 * Expected result should be 1
	 */
	@Test
	public void valid_to_integerTest() {
		uo = new UserOptions();
		int result = uo.to_integer("1");
		int expected = 1;
		assertEquals(result,expected);
		
	}
	
	/***
	 * Test for invalid change string to int
	 * Expected result should be -1
	 */
	@Test
	public void invalid_to_integerTest() {
		
		uo = new UserOptions();
		int result = uo.to_integer("String");
		int expected = -1;
		assertEquals(result,expected);
		
	}
	

	

}
