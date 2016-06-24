package com.smarttool.utils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

class CommonUtils{
	public static String getFormattedDateTime(){
		Date date=new Date(System.currentTimeMillis());
		DateFormat formatter = new SimpleDateFormat("MMMM dd,YY,hh:mma");
		String dateFormatted = formatter.format(date);
		return dateFormatted;
		
	}
	public static String generatePin(){
		Random generator = new Random();
		generator.setSeed(System.currentTimeMillis());
		return String.valueOf(100000+generator.nextInt(900000));
	}
}