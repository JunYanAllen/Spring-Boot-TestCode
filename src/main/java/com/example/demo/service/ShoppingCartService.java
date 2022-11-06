package com.example.demo.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {
	
	private String scarNo;
	
	public ShoppingCartService() throws ParseException{
		if(this.scarNo == null) {
			createScarNo();
		}
	}
		
	public ShoppingCartService getinit() throws ParseException{
		ShoppingCartService shoppingCartService = new ShoppingCartService();
		return shoppingCartService;
	}
	
	public void createScarNo() throws ParseException {
		long mstime = System.currentTimeMillis();
		long s2 = mstime / 1000;
		double s1 = (mstime-(s2*1000)) / 1000d;
		
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = dFormat.parse("2014-10-20");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		long timestamp = calendar.getTimeInMillis();
		
		int rand = (int)(Math.random() * 9000) + 1000;
		String t = String.format("%.0f", (s1 + s2) * 1000);
		this.scarNo = (Long.parseLong(t) - timestamp) + Integer.toString(rand);
	}
	
	public void setScarNo(String scarNo) {
		this.scarNo = scarNo;
	}
	
	public String getScarNo() {
		return this.scarNo;
	}
}
