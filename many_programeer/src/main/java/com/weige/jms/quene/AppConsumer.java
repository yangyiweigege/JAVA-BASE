package com.weige.jms.quene;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消费消息
 * @author 杨乙伟
 * @Version 1.0 beta
 * @Date 2017年9月15日
 * @Time 下午12:07:00
 */
public class AppConsumer
{
	private final static String URL="tcp://127.0.0.1:61616"; 
	private final static String QUEUE_NAME="queue";
	public static void main(String[] args) throws JMSException
	{
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
		//6.创建一个消费者
		MessageConsumer consumer= session.createConsumer(destination);
		//7.创建一个监听器
		consumer.setMessageListener(new MessageListener()
		{
			public void onMessage(Message message)
			{
				TextMessage textMessage=(TextMessage) message;
				try
				{
					System.out.println("接受消息:"+textMessage.getText());
				} catch (JMSException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		//9.关闭连接
		//connection.close();
		
	}
}
