package com.proxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description: 动态代理测试
 * @Author: 杨乙伟
 * @Date Created in 2019-01-19 15:12:43
 */
public class DamicProxyTest {

    public static  void changeArg(Object obj, Object... args) {
        System.out.println("obj: " + obj);
        System.out.println("args:" + args.length);
    }


    public static  void main(String[] args) throws NoSuchMethodException {

        Fly fly = new FlyPig();
        Class<?> flyClass = fly.getClass();
        System.out.println("class:" + flyClass.getName());
        Method method = flyClass.getMethod("fly");
        RunAnnotation runAnnotation = method.getAnnotation(RunAnnotation.class);
//        System.out.println(runAnnotation.value());
        CountFlyTimeAop countFlyTimeAop = new CountFlyTimeAop(fly);
        Fly flyProxy = (Fly) Proxy.newProxyInstance(flyClass.getClassLoader(), flyClass.getInterfaces(), countFlyTimeAop);
        flyProxy.fly();
        // changeArg(1,2,3,4,8);
    }


}
