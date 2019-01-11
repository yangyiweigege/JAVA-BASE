package com.weige.spring.jms.jms_spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CountRepateNumber {

	public static void main(String[] args) {
		/*List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("a");
		list.add("a");
		list.add("b");
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < list.size(); i++) {
			map.put(list.get(i), 0);
		}

		for (int i = 0; i < list.size(); i++) {
			for (String item : map.keySet()) {
				
				if (list.get(i).equals(item)) {
					int count = (int) map.get(item);
					count++;
					map.put(item, count);
				}
				
			}
		}
		
		for (Map.Entry<String, Object> item : map.entrySet()) {
			System.out.println("key：" + item.getKey() + "  value :" + item.getValue());
		}*/
		

        Set<String> result = new HashSet<String>();
        Set<String> set1 = new HashSet<String>() {
            {
                add("王者荣耀");
                add("英雄联盟");
                add("穿越火线");
                add("地下城与勇士");
            }   
        };

        Set<String> set2 = new HashSet<String>() {
            {
                add("王者荣耀");
                add("地下城与勇士");
                add("魔兽世界");
            }
        };

        result.clear();
        result.addAll(set1);
        result.retainAll(set2);
        System.out.println("交集：" + result);

        result.clear();
        result.addAll(set1);
        result.removeAll(set2);
        System.out.println("差集：" + result);

        result.clear();
        result.addAll(set1);
        result.addAll(set2);
        System.out.println("并集：" + result);
        
        Set<Integer> oneSet = new HashSet<Integer>();
        oneSet.add(1);
        oneSet.add(2);
        oneSet.add(3);
        Set<Integer> twoSet = new HashSet<Integer>();
        twoSet.add(1);
        oneSet.retainAll(twoSet);
        //oneSet.clear();
        System.out.println(oneSet);
	}

}
