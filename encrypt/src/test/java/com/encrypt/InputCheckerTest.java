package com.encrypt;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

public class InputCheckerTest {
	
	InputChecker ic ; 
	
	@Before
	public void create()
	{
		ic = mock(InputChecker.class);
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
		
		when(ic.option_selected(4)).thenReturn("error");
		assertEquals(ic.option_selected(4),"error");		
	}
	
	/***
	 * Test for choosing valid option 
	 * Expected result should be "encryption"
	 * 
	 */
	@Test
	public void valid_option_selectionTest() {
		when(ic.option_selected(1)).thenReturn("encryption");
		assertEquals(ic.option_selected(1),"encryption");
	}
	/***
	 * Test for valid change string to int
	 * Expected result should be 1
	 */
	@Test
	public void valid_to_integerTest() {
			
		when(ic.to_integer("1")).thenReturn(1);
		assertEquals(ic.to_integer("1"),1);
		
	}
	
	/***
	 * Test for invalid change string to int
	 * Expected result should be -1
	 */
	@Test
	public void invalid_to_integerTest() {
		when(ic.to_integer("string")).thenReturn(-1);
		assertEquals(ic.to_integer("string"),-1);
		
	}
	

	

}
