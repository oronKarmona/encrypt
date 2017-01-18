package com.encrypt.Ciphers;

import java.util.ArrayList;
import java.util.Scanner;

import com.encrypt.UserOptions;

import lombok.Getter;
import lombok.Setter;

public abstract class AbstractDouble extends Cipher
{
	@Getter @Setter protected ArrayList<Cipher> ciphers;
	ArrayList<Byte> keys ;
	
	public AbstractDouble(Cipher first , Cipher second)
	{
		ciphers = new ArrayList<Cipher>();
		ciphers.add(first);
		ciphers.add(second);
		keys = new ArrayList<Byte>();
	}
	@Override
	public ArrayList<Byte> getKeys()
	{
		return this.keys;
	}
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
	
	
	public void setKeys(ArrayList<Byte> keys)
	{
		
		this.keys = keys ;
	
	}
}
