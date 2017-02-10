package com.encrypt.Ciphers;

import java.util.ArrayList;
import java.util.Scanner;

import com.encrypt.UserOptions;

import lombok.Getter;
import lombok.Setter;

/***
 * Abstract class for the double cipher algorithms 
 * @author Oron
 *
 */
public abstract class AbstractDouble extends Cipher
{
	@Getter @Setter protected ArrayList<Cipher> ciphers;
	
	/***
	 * Constructor
	 * @param first - first cipher to act
	 * @param second - second cipher to act
	 */
	public AbstractDouble(Cipher first , Cipher second)
	{
		ciphers = new ArrayList<Cipher>();
		ciphers.add(first);
		ciphers.add(second);
		keys = new ArrayList<Byte>();
	}
	
	/***
	 * Creating the keys for each cipher
	 */
	@Override
	public void createKey() {
		
		for(Cipher c : ciphers)
		{
			if(c!=null)
			{
				c.createKey();
				keys.add(c.getKey());
			}
		}
		
		
	}

}
