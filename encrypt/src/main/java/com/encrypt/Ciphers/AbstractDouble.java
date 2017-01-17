package com.encrypt.Ciphers;

import java.util.ArrayList;
import java.util.Scanner;

import com.encrypt.UserOptions;

import lombok.Getter;
import lombok.Setter;

public abstract class AbstractDouble extends Cipher
{
	@Getter @Setter ArrayList<Cipher> ciphers;
	
	public AbstractDouble(Cipher first , Cipher second)
	{
		ciphers = new ArrayList<Cipher>();
		ciphers.add(first);
		ciphers.add(second);
	}
	
	@Override
	public void createKey() {
		
		for(Cipher c : ciphers)
		{
			if(c!=null)
				c.createKey();
		}
	}
	
	
	public void setKeys()
	{
		Scanner in = new Scanner(System.in);
		UserOptions uo = new UserOptions();
		byte k = -1 ; 
		for(Cipher c : ciphers)
		{
			if(c!=null)
				c.decryptionKey();
		}
	}
}
