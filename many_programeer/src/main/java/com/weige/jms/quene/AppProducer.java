package com.weige.jms.quene;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;


public class AppProducer
{
	private final static String URL="tcp://127.0.0.1:61616"; 
	private final static String QUEUE_NAME="queue";
	public static void main(String[] args) throws JMSException
	{
	    System.out.println("项目启动");
		//1.创建连接工厂
		ActiveMQConnectionFactory connectionFactory=new ActiveMQConnectionFactory(URL);
		//2.创建Connection
		Connection connection=connectionFactory.createConnection();
		//3.启动连接
		connection.start();
		//4.创建会话
		Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5.创建一个目标
		Destination destination=session.createQueue(QUEUE_NAME);
		//6.创建一个生产者
		MessageProducer producer= session.createProducer(destination);
		for (int i = 0; i < 100; i++)
		{
			//7.创建消息
			TextMessage textMessage=session.createTextMessage("test"+i);
			//8.发布消息
			producer.send(textMessage);
			
			System.out.println("消息发送成功:"+textMessage.getText());
		}
		//9.关闭连接
		connection.close();
	
	}
}
