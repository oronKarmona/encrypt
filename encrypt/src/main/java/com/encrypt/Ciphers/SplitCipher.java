package com.encrypt.Ciphers;

import java.util.ArrayList;

public class SplitCipher extends AbstractDouble{

	ArrayList<Byte> keys ;
	byte[] odds ;
	byte[] even ;
	
	public SplitCipher(Cipher first, Cipher second) {
		super(first, second);
		
		keys = new ArrayList<Byte>();
	}

	@Override
	public byte[] encrypt() throws Exception 
	{
		Cipher c = ciphers.get(0);
		int odd_index = 0 , even_index = 0 ;
		int odd_ctr = 0 , even_ctr = 0 ; 
		
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
		
		return input;
	}

	@Override
	public String decrypt() throws Exception 
	{
		Cipher c = ciphers.get(0);
		int odd_index = 0 , even_index = 0 ;
		int odd_ctr = 0 , even_ctr = 0 ; 
		
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
		
       return out;
		
	}
	
	@Override 
	public void createKey()
	{
		for(int i = 0 ; i< 2 ; i++)
		{
			System.out.print((i+1)+".");
			ciphers.get(0).createKey();
			keys.add(ciphers.get(0).getKey());
		}
	}
	
	@Override 
	public void setKeys()
	{
		for(int i = 0 ; i< 2 ; i++)
		{
			System.out.print((i+1)+".");
			ciphers.get(0).decryptionKey();
			keys.add(ciphers.get(0).getKey());
		}
	}

}
