package MultiFiles;



import java.util.ArrayList;
import java.util.Observable;

import com.encrypt.FileManager;
import com.encrypt.Ciphers.Operations;

import lombok.Data;
import lombok.Getter;
/***
 * Abstract class which each cipher algorithm will extend it
 * @author Oron
 *
 */
@Data
public abstract class Cipher extends Observable implements Operations,Cloneable{

	@Getter protected final int maximalValue = Byte.MAX_VALUE + 0 ;
	@Getter protected final int minimalValue =  Byte.MIN_VALUE  + 0;
	protected byte key; 
	protected ArrayList<Byte> keys = null;
    protected byte[] input;
	protected byte[] output;
	protected long start_time ; 
	protected FileManager fm;
	
	public Cipher()
	{
		keys = new ArrayList<Byte>();
	}
	public void createKey() {
	
		this.key = (byte) ((Math.random() * maximalValue) + minimalValue);
		//System.out.println("Your encryption key for " +getName() +" is: " + (int)key);
		this.keys.add(this.key);
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
	
	
	public String getName()
	{
		String s = this.getClass().getName();
		String[] names = s.split("\\.");
		
		return names[names.length - 1 ];
	}
	
	public void decryptionKey(ArrayList<Byte> keys)
	{
		
		this.key = keys.get(0);
	
	}
	
	public byte[] getByteArrayKey()
	{
		byte[] b = new byte[keys.size()];
		
		for(int i = 0 ; i < b.length ; i++)
			b[i] = (byte)keys.get(i);
		
		return b ; 
	} 
	
	@Override
	protected Object clone() throws CloneNotSupportedException {

	    return super.clone();
	}

}