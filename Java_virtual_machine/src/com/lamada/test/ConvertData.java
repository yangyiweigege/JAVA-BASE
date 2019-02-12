package com.lamada.test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: xxx
 * @Author: 杨乙伟
 * @Date Created in 2019-01-17 20:21:29
 */

public class ConvertData {

    private int id;


    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void xxx(OneInterface oneInterface) {

    }

    public static void main(String[] args) {
        List<ConvertData> list = new ArrayList();
        ConvertData convertData = new ConvertData();
        convertData.setId(2);
        convertData.setName("杨");
        ConvertData convertData2 = new ConvertData();
        convertData2.setId(2);
        convertData2.setName("杨2");
        ConvertData convertData1 = new ConvertData(){

            @Override
            public void xxx(OneInterface oneInterface) {
                System.out.println("大家好");
            }
        };
        convertData1.xxx((String a, String b) -> { return  "hello";});
        list.add(convertData);
        list.add(convertData2);
       /* Map<Integer, ConvertData> map = list.stream().collect(Collectors.toMap(ConvertData::getId, x -> x));
        System.out.println(map);*/
        Set<Integer> set = list.stream().map(ConvertData::getId).collect(Collectors.toSet());
        System.out.println(set);
        Map<Integer, ConvertData> map1 = new HashMap<>();
        list.forEach( x -> {
            map1.put(x.getId(), x);
        });
        System.out.println(map1);
    }

}
