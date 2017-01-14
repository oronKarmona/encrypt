package com.encrypt.Ciphers;

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
		output = encrypt();
		
		return (new String(output));
	}

}
