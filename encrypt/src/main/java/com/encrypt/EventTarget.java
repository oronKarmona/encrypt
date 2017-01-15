package com.encrypt;

import java.util.Observable;
import java.util.Observer;

import lombok.Getter;

public class EventTarget implements Observer
{

	@Override
	public void update(Observable arg0, Object arg) {
	
		if(arg instanceof String)
		{
			String msg = (String)arg;
			System.out.println(msg);
		}
		
	}

}
