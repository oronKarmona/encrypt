package com.encrypt.Ciphers;

public interface Operations {

	/***
	 * Encrypts the input with selected encryption algorithm
	 * @return encrypted byte array
	 * @throws Exception 
	 */
	public byte[] encrypt() throws Exception;
	/***
	 * Decrypts the input with selected decryption algorithm
	 * @return decrypted byte array
	 * @throws Exception if the user the key is dividable by 2 or equals 0.
	 */
	public String decrypt() throws Exception;
	/***
	 * Creates key from value min to max value of char.
	 * return the key generated.
	 */
	public void createKey();
}
