package com.encrypt;


import lombok.Data;

@Data
public abstract class Cipher {
	
	protected byte key; 
    protected byte[] input;
	protected byte[] output;
	
	public abstract byte createKey();
	public abstract byte[] encrypt();
	public abstract String decrypt();

}
