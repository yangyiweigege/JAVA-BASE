package com.spring.jms.producer;
/**
 * 生产者接口
 * @author 杨乙伟
 * @Version 1.0 beta
 * @Date 2017年9月15日
 * @Time 下午7:44:28
 */
public interface ProducerService
{
	public abstract void sendMessage(String message);
}
