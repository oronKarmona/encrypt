package com.encrypt;

import java.util.ArrayList;

public class ErrorMSG {

	
	public static String msg ;
	public static String ecxeption;
	
	
	public static void addEx(String msg , String e)
	{
		ErrorMSG.msg += "\n "+msg;
		ErrorMSG.ecxeption += "\n "+e;
	}
}
