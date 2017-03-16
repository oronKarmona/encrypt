package com.encrypt.Ciphers;

import java.util.Arrays;

import MultiFiles.Cipher;

public class XorCipher extends Cipher{

	@Override
	public byte[] encrypt()
	{
	
		start("Encryption");
		for(int i=0 ; i < input.length ; i++)
		{
			input[i] = (byte)(((int)input[i])  ^ ((int)this.getKey()));
		}
		 
		End("Encryption");
		return input;
	}

	@Override
	public String decrypt() 
	{
		byte[] temp = input;
		
		start("Decryption");
		for(int i=0 ; i < input.length ; i++)
		{
			temp[i] = (byte)(((int)input[i])  ^ ((int)this.getKey()));
		}
		End("Decryption");
		
		output = temp ; 
		return (new String(output));
	}

}
