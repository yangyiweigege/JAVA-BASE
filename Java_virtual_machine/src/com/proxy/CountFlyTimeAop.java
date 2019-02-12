package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description: 统计飞行时间代理aop
 * @Author: 杨乙伟
 * @Date Created in 2019-01-19 15:15:07
 */
public class CountFlyTimeAop implements InvocationHandler {

    private Object target;

    public CountFlyTimeAop(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始计算飞行时间....");
        method.invoke(target, args);
        System.out.println("计算飞行时间结束");
        return "aaaa";
    }
}
