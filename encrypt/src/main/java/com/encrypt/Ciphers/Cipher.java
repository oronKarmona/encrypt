package com.encrypt.Ciphers;


import java.util.Observable;

import lombok.Data;
import lombok.Getter;
/***
 * Abstract class which each cipher algorithm will extend it
 * @author Oron
 *
 */
@Data
public abstract class Cipher extends Observable implements Operations{

	@Getter protected final int maximalValue = Byte.MAX_VALUE + 0 ;
	@Getter protected final int minimalValue =  Byte.MIN_VALUE  + 0;
	protected byte key; 
    protected byte[] input;
	protected byte[] output;
	protected long start_time ; 
	
	public byte createKey() {
	
		this.key = (byte) ((Math.random() * maximalValue) + minimalValue);
		return key;
	}
	
	public void start(String msg)
	{
		start_time = System.nanoTime();
		publish(msg + " Started");
	}
	
	public void End(String msg)
	{
		long total_time = System.nanoTime() - start_time;
		publish(msg + " Finished - Total time: " + ((total_time)/(double)1000000) +" ms");
	}
	
	public void publish(String msg)
	{
		setChanged();
		notifyObservers(msg);
	}

}
