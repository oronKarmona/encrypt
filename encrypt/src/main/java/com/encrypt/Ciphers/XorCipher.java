package com.encrypt.Ciphers;

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
		start("Decryption");
		for(int i=0 ; i < input.length ; i++)
		{
			input[i] = (byte)(((int)input[i])  ^ ((int)this.getKey()));
		}
		End("Decryption");
		
		return (new String(input));
	}

}
