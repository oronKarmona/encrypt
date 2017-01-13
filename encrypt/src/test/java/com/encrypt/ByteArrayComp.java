package com.encrypt;

import java.util.Comparator;

public class ByteArrayComp implements Comparator<byte[]>{

	@Override
	public  int compare(byte[] a, byte[] b) {
		if(a.length != b.length)
			return -1;
		for(int i = 0 ; i < a.length ; i++)
		{
			if(a[i] != b[i])
				return 0 ;
		}
		
		return 1;
	}

}
