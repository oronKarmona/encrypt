package com.encrypt.Ciphers;

public class MWOCipher extends Cipher{

	@Override
	public byte[] encrypt() {
		
		byte[] temp = this.getInput();
		int mul; 
		start("Encryption");
		for(int i = 0 ; i < temp.length ; i++)
		{
			mul = (((int)input[i]) * ((int)this.getKey()));
			temp[i] = (byte)mul;
		}
		
		End("Encryption");
		this.setInput(temp); 
		return this.getInput();
	}

	@Override
	public String decrypt() throws Exception{
		
		byte dec_key = 0;
		int mul ;
		
		if(this.getKey() %2 == 0 || this.getKey() == 0 )
				throw new Exception();
		start("Decryption");
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
		for(int i = 0 ; i < input.length ; i++)
		{
			input[i] = (byte)(((int)input[i]) * ((int)dec_key));
		}
		
		End("Decryption");
		this.setOutput(input);
		return new String(output);
	}
	
	/***
	 * Creates key from value min to max value of char.
	 * return the key generated.
	 */
	
	public void createKey() {
		boolean status= false;
		while(!status)
		{
			this.key = (byte) ((Math.random() * maximalValue) + minimalValue);
			if(key%2 != 0 && key != 0 )
				status = true;
		}
			
		System.out.println("Your encryption key for "+getName()+ " is: " + (int)key);
		keys.add(key);
	}

}
