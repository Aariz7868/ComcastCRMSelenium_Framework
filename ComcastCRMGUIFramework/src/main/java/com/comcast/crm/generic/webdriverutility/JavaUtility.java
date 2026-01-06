package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
	Date dateObj= new Date();
	public  int getRandomNmber() {
		Random random= new Random();
		int randomNumber=random.nextInt(5000);
		
		return randomNumber;
	}
	
	public String getSystemDateYYYYDDMM() {
		
		String date= sdf.format(dateObj);
		
		return date;
		}
	
	public String getRequiredDateYYYYMMDD(int days) {
	
		Calendar cal=sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String reqDate= sdf.format(cal.getTime());
		
		return reqDate;
		
	}

}
