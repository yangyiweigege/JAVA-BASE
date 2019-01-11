package com.weige.lamdba.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BaseModel<T> {
	protected T key;
	

	public T getKey() {
		return key;
	}

	public void setKey(T key) {
		this.key = key;
	}
	
	public  static <A> List<A> getT(Class<A> class1) {
		try {
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static void main(String[] args) {
	
		BaseModel baseModel = new BaseModel<>();
		Field fields[] = baseModel.getClass().getDeclaredFields();
		for (Field field : fields) {
			System.out.println( field.getName() + "   " + field.getType().getName());
		}
	}

}
