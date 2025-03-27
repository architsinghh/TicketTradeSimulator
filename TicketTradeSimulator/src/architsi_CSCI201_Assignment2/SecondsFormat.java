package architsi_CSCI201_Assignment2;


public class SecondsFormat {
	
	static long millisecsPerSec = 1000;
	static long milliSecsPerMin = millisecsPerSec * 60;
	static long millisecsPerHour = milliSecsPerMin * 60;

	public static String getSecondsFormat(long start) {
		
		long time = System.currentTimeMillis() - start;
		
		
	    long hours = time / millisecsPerHour;
	    long mins = (time % millisecsPerHour) / milliSecsPerMin;
	    long secs = (time % milliSecsPerMin) / millisecsPerSec;
	    long millisecs = time % millisecsPerSec;
	    
	    String hourStr = String.format("%02d" , hours);
	    String minStr = String.format("%02d" , mins);
	    String secStr = String.format("%02d" , secs);
	    String millisecsStr = String.format("%03d" , millisecs);
	    
	    String combinedTimeStr = hourStr + ":" + minStr + ":" + secStr + ":" + millisecsStr;
	    return combinedTimeStr;
	   
	}

}

