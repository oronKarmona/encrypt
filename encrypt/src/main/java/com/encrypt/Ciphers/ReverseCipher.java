package com.encrypt.Ciphers;

public class ReverseCipher extends AbstractDouble
{

	public ReverseCipher(Cipher first, Cipher second) {
		super(first, second);

	}

	@Override
	public byte[] encrypt() throws Exception {
		
		Cipher temp = this.ciphers.get(0);
		temp.setInput(input);
		temp.decrypt();
		
		return temp.getInput();
	}

	@Override
	public String decrypt() throws Exception {
		
		Cipher temp = this.ciphers.get(0);
		temp.setInput(input);
		
		String str = new String(temp.encrypt());
		
		return str;
	}

}
