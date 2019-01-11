package com.spring.jms.producer;
import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class ProducerServiceBean implements ProducerService
{
	@Resource
	private JmsTemplate JmsTemplate;
	@Resource(name="queueDestination")
	private Destination destination;
	@Override
	public void sendMessage(final String message)
	{
		JmsTemplate.send(destination,new MessageCreator()
		{			
			@Override//匿名类
			public Message createMessage(Session session) throws JMSException
			{
				TextMessage textMessage=session.createTextMessage(message);
				System.out.println("消息发送成功！"+textMessage.getText());
				return textMessage;
			}
		});
	}

}
