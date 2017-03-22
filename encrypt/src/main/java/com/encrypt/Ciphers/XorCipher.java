package com.encrypt.Ciphers;

import java.util.Arrays;

public class XorCipher extends Cipher{

	@Override
	public byte[] encrypt()
	{
	
		
		for(int i=0 ; i < input.length ; i++)
		{
			input[i] = (byte)(((int)input[i])  ^ ((int)this.getKey()));
		}
		 
		
		return input;
	}

	@Override
	public String decrypt() 
	{
		byte[] temp = input;
		
		
		for(int i=0 ; i < input.length ; i++)
		{
			temp[i] = (byte)(((int)input[i])  ^ ((int)this.getKey()));
		}
		
		
		output = temp ; 
		return (new String(output));
	}

}
