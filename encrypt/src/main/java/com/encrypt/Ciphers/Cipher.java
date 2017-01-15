package com.encrypt.Ciphers;


import lombok.Data;
import lombok.Getter;
/***
 * Abstract class which each cipher algorithm will extend it
 * @author Oron
 *
 */
@Data
public abstract class Cipher implements Operations{

	@Getter protected final int maximalValue = Byte.MAX_VALUE + 0 ;
	@Getter protected final int minimalValue =  Byte.MIN_VALUE  + 0;
	protected byte key; 
    protected byte[] input;
	protected byte[] output;
	
	
	public byte createKey() {
	
		this.key = (byte) ((Math.random() * maximalValue) + minimalValue);
		return key;
	}

}
