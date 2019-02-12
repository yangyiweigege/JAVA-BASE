package com.proxy;

import com.lamada.test.LambdaTest;

/**
 * @Description: 会飞的猪
 * @Author: 杨乙伟
 * @Date Created in 2019-01-19 15:13:22
 */
public class FlyPig extends LambdaTest implements Fly {
    @Override
   // @RunAnnotation("abc")
    public int fly() {

       // getCglib();
        System.out.println("会飞的猪....");
        return 111;
    }


}
