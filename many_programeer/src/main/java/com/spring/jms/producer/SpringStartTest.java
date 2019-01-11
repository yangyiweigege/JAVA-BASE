package com.spring.jms.producer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringStartTest
{
	public static void main(String[] args)
	{
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("producer.xml");
		ProducerService producerService=context.getBean(ProducerService.class);
		for (int i = 0; i < 100; i++)
		{
			producerService.sendMessage("发送的消息："+i);
		}
		context.close();
	}
}
