package sbs.src.service;

public class otpGenerator {
  
	public static String generatorOTP(){
	long timeSeed = System.nanoTime(); 

    double randSeed = Math.random() * 1000; 

    long midSeed = (long) (timeSeed * randSeed);
                                                 
    String s = midSeed + "";
    String subStr = s.substring(0,6);

    Integer timeseed = Integer.parseInt(subStr); 
    return timeseed.toString();
	}
}
