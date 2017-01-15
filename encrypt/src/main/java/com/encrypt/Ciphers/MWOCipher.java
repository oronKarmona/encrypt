package com.encrypt.Ciphers;

public class MWOCipher extends Cipher{

	@Override
	public byte[] encrypt() {
		
		byte[] temp = this.getInput();
		int mul; 
		for(int i = 0 ; i < temp.length ; i++)
		{
			mul = (((int)input[i]) * ((int)this.getKey()));
			temp[i] = (byte)mul;
		}
		
		this.setInput(temp); 
		return this.getInput();
	}

	@Override
	public String decrypt() throws Exception{
		
		byte dec_key = 0;
		byte[] temp = input;
		int mul ;
		
		if(this.getKey() %2 == 0 || this.getKey() == 0 )
				throw new Exception();
		
		// finding the opposite number to the key used in enryption
		for(int i = this.getMinimalValue(); i <= this.getMaximalValue();i++)
		{
			mul = i * ((int)this.getKey());
			if((byte)mul  == 1 )
			{
				
				dec_key = (byte) i ; 
				break;
			}
		}
		
		//decryption
		for(int i = 0 ; i < temp.length ; i++)
		{
			temp[i] = (byte)(((int)input[i]) * ((int)dec_key));
		}
		
		this.setOutput(temp);
		return new String(output);
	}
	
	/***
	 * Creates key from value min to max value of char.
	 * return the key generated.
	 */
	
	public byte createKey() {
		boolean status= false;
		while(!status)
		{
			this.key = (byte) ((Math.random() * maximalValue) + minimalValue);
			if(key%2 != 0 && key != 0 )
				status = true;
		}
			
		return key;
	}

}
