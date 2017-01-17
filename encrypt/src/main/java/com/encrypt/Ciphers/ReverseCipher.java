package com.encrypt.Ciphers;

public class ReverseCipher extends AbstractDouble
{

	public ReverseCipher(Cipher first, Cipher second) {
		super(first, second);

	}

	@Override
	public byte[] encrypt() throws Exception {
		start("Encrption");
		Cipher temp = this.ciphers.get(0);
		temp.setInput(input);
		temp.decrypt();
		End("Encrption");
		return temp.getInput();
	}

	@Override
	public String decrypt() throws Exception {
		start("Decrption");
		Cipher temp = this.ciphers.get(0);
		temp.setInput(input);
		
		String str = new String(temp.encrypt());
		End("Decrption");
		return str;
	}

}
