package com.encrypt.Ciphers;

import java.util.Arrays;

import MultiFiles.Cipher;



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
		
		byte[] temp = Arrays.copyOf(input,input.length);
		
		start("Encryption");
		int t ; 
		for(int i = 0 ; i < temp.length ;i++)
		{
			if( (this.getKey() + temp[i]) > maximalValue)
			{
				 t =  (((this.getKey() + temp[i]) - maximalValue) + minimalValue);

			}
			else
			{
				t =  (this.getKey() + temp[i]);
			}
			
			temp[i] = (byte)t;
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
		byte[] temp = input;
		int a,b; 
		
		start("Decryption");
		int t ; 
		for(int i = 0 ; i < temp.length ; i++)
		{
			if (temp[i]-this.getKey() < minimalValue)
			{
				a = temp[i] - this.getKey();
				b = a - minimalValue;
				t = (maximalValue +b) ;
			}
			
			else
			{
				t = (temp[i] - this.getKey());
			}
			temp[i] =(byte) t ;
		}
		
		End("Decryption");
		output = temp ; 
		String s = new String(output);
			return s;
	}

	

}
