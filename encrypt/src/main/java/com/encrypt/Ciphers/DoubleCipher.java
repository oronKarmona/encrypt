package com.encrypt.Ciphers;


public class DoubleCipher extends AbstractDouble{

	public DoubleCipher(Cipher first, Cipher second) {
		super(first, second);
		
	}

	@Override
	public byte[] encrypt() throws Exception 
	{
		
		for(Cipher c : ciphers)
		{
			c.setInput(input);
			this.setInput(c.encrypt());
		}
		
		return input;
	}

	@Override
	public String decrypt() throws Exception {
	

		for(int i = ciphers.size() - 1 ; i >=0 ;i--)
		{
			ciphers.get(i).setInput(input);
			ciphers.get(i).setKey(keys.get(i));
			ciphers.get(i).decrypt();
			this.setInput(ciphers.get(i).getOutput());
		}
		
		this.output = input;
		
		return new String(output);
	}

}
