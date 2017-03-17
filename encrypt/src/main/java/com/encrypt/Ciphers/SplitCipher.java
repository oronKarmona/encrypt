package com.encrypt.Ciphers;

import java.util.ArrayList;

/***
 * Split cipher - ciphers the odds bytes with one key and the evens with another
 * @author Oron
 *
 */
public class SplitCipher extends AbstractDouble{

	
	byte[] odds ;
	byte[] even ;
	
	/***
	 * constructor
	 * @param first - method for encryption
	 * @param second - method for encryption
	 */
	public SplitCipher(Cipher first, Cipher second) {
		super(first, second);

	}
	
	

	@Override
	public byte[] encrypt() throws Exception 
	{
		Cipher c = ciphers.get(0);
		int odd_index = 0 , even_index = 0 ;
		int odd_ctr = 0 , even_ctr = 0 ; 
		start("Encryption");
		//counting odds and evens 
		for(int i=0 ; i<input.length ; i++)
		{
			if(i % 2 != 0)
				odd_ctr ++ ;
			else
				even_ctr ++;
		}
		
		//setting the spllited arrays
		odds = new byte[odd_ctr];
		even = new byte[even_ctr];
		
		//splitting the odds and evens
		for(int i = 0 ; i < input.length ; i++)
		{
			if(i % 2 != 0)
				odds[odd_index++] = input[i];
			else
				even[even_index++] = input[i];
		}
		//encrypting the odds
		c.setInput(odds);
		c.setKey(keys.get(0));
		odds = c.encrypt();
		//encrypting the evens
		c.setInput(even);
		c.setKey(keys.get(1));
		even = c.encrypt();
		
		even_index = odd_index = 0 ;
				//merging the odds and evens
				for(int i = 0 ; i < input.length ; i++)
				{
					if(i % 2 != 0)
						input[i] = odds[odd_index++];
					else
						input[i] = even[even_index++];
				}
	    End("Encryption");
		return input;
	}

	@Override
	public String decrypt() throws Exception 
	{
		Cipher c = ciphers.get(0);
		int odd_index = 0 , even_index = 0 ;
		int odd_ctr = 0 , even_ctr = 0 ; 
		
		start("Decryption");
		//counting odds and evens 
		for(int i=0 ; i<input.length ; i++)
		{
			if(i % 2 != 0)
				odd_ctr ++ ;
			else
				even_ctr ++;
		}
		
		//setting the spllited arrays
		odds = new byte[odd_ctr];
		even = new byte[even_ctr];
		
		//splitting the odds and evens
		for(int i = 0 ; i < input.length ; i++)
		{
			if(i % 2 != 0)
				odds[odd_index++] = input[i];
			else
				even[even_index++] = input[i];
		}
		
		//decrypting the odds
		c.setInput(odds);
		c.setKey(keys.get(0));
		c.decrypt();
		odds = c.getInput();
		//decrypting the evens
		c.setInput(even);
		c.setKey(keys.get(1));
		c.decrypt();
		even = c.getInput();
		
		even_index = odd_index = 0 ;
				//merging the odds and evens
				for(int i = 0 ; i < input.length ; i++)
				{
					if(i % 2 != 0)
						input[i] = odds[odd_index++];
					else
						input[i] = even[even_index++];
				}
		
       output = input ; 
       String out = new String(output);
		End("Decryption");
       return out;
		
	}
	
	@Override 
	public void createKey()
	{
		for(int i = 0 ; i< 2 ; i++)
		{
			//System.out.print((i+1)+".");
			ciphers.get(0).createKey();
			keys.add(ciphers.get(0).getKey());
		}
	}
	
	

}
