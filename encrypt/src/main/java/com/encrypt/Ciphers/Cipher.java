package com.encrypt.Ciphers;


import lombok.Data;
import lombok.Getter;
/***
 * Abstract class which each cipher algorithm will extend it
 * @author Oron
 *
 */
@Data
public abstract class Cipher {

	@Getter protected final int maximalValue = Byte.MAX_VALUE + 0 ;
	@Getter protected final int minimalValue =  Byte.MIN_VALUE  + 0;
	protected byte key; 
    protected byte[] input;
	protected byte[] output;
	/***
	 * Encrypts the input with selected encryption algorithm
	 * @return encrypted byte array
	 */
	public abstract byte[] encrypt();
	/***
	 * Decrypts the input with selected decryption algorithm
	 * @return decrypted byte array
	 */
	public abstract String decrypt();
	
	
	
	/***
	 * Creates key from value min to max value of char.
	 * return the key generated.
	 */
	
	public byte createKey() {
	
		this.key = (byte) ((Math.random() * maximalValue) + minimalValue);
		return key;
	}

}
