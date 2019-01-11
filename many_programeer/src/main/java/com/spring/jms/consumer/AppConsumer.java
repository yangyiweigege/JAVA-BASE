package com.spring.jms.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.jms.producer.ProducerService;

public class AppConsumer
{
	public static void main(String[] args)
	{
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("consumer.xml");
		System.out.println("执行完毕 ");
	}
}
