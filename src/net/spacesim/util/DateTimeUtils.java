package net.spacesim.util;

public class DateTimeUtils {
	
	public static int getSeconds(long time) {
		return (int) (time % 60);
	}
	
	public static int getMinutes(long time) {
		return (int) ((time / 60) % 60);
	}
	
	public static int getHours(long time) {
		return (int) ((time / 3600) % 24);
	}
	
	public static int getDays(long time) {
		return (int) ((time / 86400) % 365);
	}
	
	public static int getYears(long time) {
		return (int) (time / 31536000);
	}
	
	public static String getTimeString(long time) {
		return String.format("%02d:%02d:%02d", getHours(time), getMinutes(time), getSeconds(time));
	}

	public static String getDateString(long time) {
		return String.format("%d/%d", getDays(time), getYears(time));
	}
	
}