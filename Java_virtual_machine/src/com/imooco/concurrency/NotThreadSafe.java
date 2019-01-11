package com.imooco.concurrency;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
 /**
  * 慕课网线程安全学习
  * @author yangyiwei
  * @date 2018年11月1日
  * @time 下午7:45:59
  */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotThreadSafe {
	
	String value() default "";

}
