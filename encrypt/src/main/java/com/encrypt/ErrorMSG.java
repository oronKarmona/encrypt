package com.encrypt;

import java.util.ArrayList;

public class ErrorMSG {

	
	public static ArrayList<String> msg ;
	public static ArrayList<String> ecxeption;
	
	
	public static void addEx(String msg , String e)
	{
		ErrorMSG.msg.add(msg);
		ErrorMSG.ecxeption.add(e);
	}
}
