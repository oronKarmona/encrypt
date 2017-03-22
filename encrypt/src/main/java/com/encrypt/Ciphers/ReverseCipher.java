package com.encrypt.Ciphers;


/***
 * Reverse cipher algorithm . 
 * encrypts with given cipher decryption  
 * decrypts with given cipher encryption 
 * @author Oron
 *
 */
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
		temp.setKey(keys.get(0));
		this.output = temp.encrypt();
		
		return new String(output);
	}

}
