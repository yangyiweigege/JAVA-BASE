package com.weige.node.test;
/**
 * �ڵ�
 * @author Administrator
 *
 */
public class Node<T>
{
	/**
	 * ������
	 */
	private T data;
	
	/**
	 * ��һ���ڵ�
	 */
	private Node<T> nextNode;
	
	public Node()
	{
		
	}
	
	

	public T getData()
	{
		return data;
	}



	public void setData(T data)
	{
		this.data = data;
	}





	public Node<T> getNextNode()
	{
		return nextNode;
	}

	public void setNextNode(Node<T> nextNode)
	{
		this.nextNode = nextNode;
	}

	@Override
	public String toString()
	{
		return "Node [data=" + data + ", nextNode=" + nextNode + "]";
	}
	
	
	
}
