package com.encrypt.Ciphers;



/***
 * This class will be used for of Ceasar Cipher algorithm
 * @author Oron
 *
 */
public class CaesarCipher extends Cipher{

	
	/***
	 * Encrypts the input with the generated key and output it
	 * Return - encrypted byte array 
	 */
	@Override
	public byte[] encrypt() 
	{
		
		byte[] temp = this.getInput();
		
		start("Encryption");

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
		
		End("Encryption");

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
		
		start("Decryption");
		
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
		
		End("Decryption");
		String s = new String(temp);
			return s;
	}

	

}