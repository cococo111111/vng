package com.services.vote;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.services.soap.mo.SOAPConstants;

public class TestLocal {
	private static final String YYYYMMDDHH = "yyyyMMddkk";

	public TestLocal() {
	}
 
	public static void main(String[] args) {
		System.out.print(new SimpleDateFormat(SOAPConstants.DATE_TIME).format(new Date()));
		
	}

}
