package com.encrypt;


import lombok.Getter;
/***
 * This class will be used for of Ceasar Cipher algorithm
 * @author Oron
 *
 */
public class CaesarCipher extends Cipher{

	@Getter private final int maximalValue = Byte.MAX_VALUE + 0 ;
	@Getter private final int minimalValue =  Byte.MIN_VALUE  + 0;
	
	
	/***
	 * Creates key from value min to max value of char.
	 * return the key generated.
	 */
	@Override
	public byte createKey() {
	
		this.key = (byte) ((Math.random() * maximalValue) + minimalValue);
		return key;
	}
	
	/***
	 * Encrypts the input with the generated key and output it
	 * Return - encrypted byte array 
	 */
	@Override
	public byte[] encrypt() 
	{
		
		byte[] temp = this.getInput();
		
		for(int i = 0 ; i < temp.length ;i++)
		{
			if( (this.getKey() + temp[i]) > maximalValue)
			{
				temp[i] = (byte) (((this.getKey() + temp[i]) - maximalValue) + minimalValue);
			}
			else
			{
				temp[i] = (byte) (this.getKey() + temp[i]);
			}
		}
		
		
		return temp;
	}

	/***
	 * Decrypts the input with the user's key
	 * Return - the decrypted srting
	 */
	@Override
	public String decrypt() 
	{
		byte[] temp = this.getInput();
		int a,b; 
		for(int i = 0 ; i < temp.length ; i++)
		{
			if (temp[i]-this.getKey() < minimalValue)
			{
				a = temp[i] - this.getKey();
				b = a -minimalValue;
				temp[i] = (byte)(maximalValue +b) ;
			}
			
			else
			{
				temp[i] = (byte)(temp[i] - this.getKey());
			}
		}
		String s = new String(temp);
			return s;
	}

	

}
