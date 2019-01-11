package com.weige.lamdba.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;

public class MapEntity {

	private int i;

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public static void main(String[] args) {
		MapEntity mapEntity = new MapEntity();
		mapEntity.setI(1);
		MapEntity mapEntity2 = new MapEntity();
		mapEntity2.setI(2);
		List<MapEntity> list = new ArrayList<>();
		list.add(mapEntity);
		list.add(mapEntity2);
		Map<Integer, MapEntity> map = list.stream().collect(Collectors.toMap(MapEntity::getI, k -> k));
		for (Map.Entry<Integer, MapEntity> item : map.entrySet()) {
			System.out.println(item.getKey() + ": value :" + JSONObject.toJSONString(item.getValue()));
		}
		new Thread(() -> {
			  
		}).start();
	}
}
