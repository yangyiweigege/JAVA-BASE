package com.spring.jms.consumer;

import java.math.BigDecimal;

public class Template<T> {

	
	public  void setT(T t){
		
	}
	
	
	public static void main(String[] args) {
		BigDecimal bigDecimal = new BigDecimal(50);
		//BigDecimal bigDecimal2 = ;
		System.out.println(bigDecimal.subtract(new BigDecimal(20)));
	}
}
