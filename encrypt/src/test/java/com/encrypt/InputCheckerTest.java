package com.encrypt;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

public class UserOptionsTest {
	
	UserOptions ic ; 
	
	@Before
	public void create()
	{
		ic = mock(UserOptions.class);
	}
	
	/**
	 * Test for invalidPath inserted by user. 
	 * For this case the expected result should be "error"
	 */
	@Test
	public void invalid_file_pathTest() {
		
		when(ic.file_path("invalidPath")).thenReturn("error");
		assertEquals(ic.file_path("invalidPath"),"error");
		
	}
	/***
	 * Test for valid file path inserted by the user
	 * For this case the expected result should be content of the file
	 */
	
	@Test
	public void valid_file_pathTest() {

		when(ic.file_path("C:\\Users\\karmo\\Desktop\\ss.txt")).thenReturn("content");
		assertEquals(ic.file_path("C:\\Users\\karmo\\Desktop\\ss.txt"),"content");
				
	}
	
	/***
	 * Test for invalid option selected by the user
	 * Expected result should be "error"
	 */
	@Test
	public void invalid_option_selectionTest() {
		ic = new UserOptions();
		String result = ic.option_selected(4);
		String expected = "error";
		assertEquals(result,expected);		
	}
	
	/***
	 * Test for choosing valid option 
	 * Expected result should be "encryption"
	 * 
	 */
	@Test
	public void valid_option_selectionTest() {
		ic = new UserOptions();
		String result = ic.option_selected(1);
		String expected = "encryption";
		assertEquals(result,expected);	
	}
	/***
	 * Test for valid change string to int
	 * Expected result should be 1
	 */
	@Test
	public void valid_to_integerTest() {
		ic = new UserOptions();
		int result = ic.to_integer("1");
		int expected = 1;
		assertEquals(result,expected);
		
	}
	
	/***
	 * Test for invalid change string to int
	 * Expected result should be -1
	 */
	@Test
	public void invalid_to_integerTest() {
		
		ic = new UserOptions();
		int result = ic.to_integer("String");
		int expected = -1;
		assertEquals(result,expected);
		
	}
	

	

}
