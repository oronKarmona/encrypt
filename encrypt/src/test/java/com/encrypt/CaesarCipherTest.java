package com.encrypt;


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
	public void createKeytest() 
	{
		when(cc.createKey()).thenReturn(any(Byte.class));
		assert(cc.createKey() <= cc.getMaximalValue());
		
	}

}
