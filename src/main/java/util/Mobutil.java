package util;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;

public class Mobutil {
	public static final String getTimediff(DateTime dt1,DateTime dt2){
		  String retStr=Hours.hoursBetween(dt1, dt2).getHours() % 24 +" : "+
				  Minutes.minutesBetween(dt1, dt2).getMinutes() % 60+ ":"+
				  Seconds.secondsBetween(dt1, dt2).getSeconds() % 60 ;
		return retStr;
		
	}
	
}
