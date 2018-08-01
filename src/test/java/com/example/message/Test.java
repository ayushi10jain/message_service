package com.example.message;

import java.sql.Timestamp;

public class Test {
	

	public static void main(String args[]) {
		Long time=new Timestamp(System.currentTimeMillis()).getTime();
		Long time2=1532938941000L;
		System.out.println("time is"+ time);
		Long diff=(time-time2)/(1000);
		System.out.println("diff is"+ diff);
		
		//if key exists in the cache get the value 
		//if (!value=null && value.getcounter>50) throw exception
		//get timestamp if current timestamp and oldtimestamp diff is 0 then key should be timestamp is old stamp only.
	}
}
