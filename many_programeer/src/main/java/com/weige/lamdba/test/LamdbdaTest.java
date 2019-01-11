package com.weige.lamdba.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import com.alibaba.fastjson.JSONObject;

class LamdbdaTest {

	private final int a;

	public LamdbdaTest(int a) {
		this.a = a;
	}

	public static void main(String[] args) {
		final Object object;
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", "yangyiwei");
		jsonObject.put("age", 10);
		String reString = "{\"name\":\"hahha\"}";
		System.out.println(reString);

		List<String> list = Arrays.asList("a", "b", "c");
		list.forEach(x -> {
			if (x.equals("a"))
				System.out.println(x);
		});
		list.forEach(System.out::println);
		System.out.println("list转map。。。。。");
		List<Double> cost = Arrays.asList(10.0, 20.0, 30.0);

		// Map<String,Integer> settingMap =
		// cost.stream().collect(Collectors.toMap(PmtSetting::getSettingKey, a
		// -> a));

		// Map<Double, Double> map = cost.stream().collect(Collectors.toMap(a,
		// a));
		cost.stream().map(x -> x + 20).forEach(x -> System.out.println(x));

		List<Object> list2 = new ArrayList<>();
		list2.add(jsonObject);

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

			}
		};

	Callable<String> callable = () -> {return "哈哈哈";};
	FutureTask<String> futureTask = new FutureTask<>(callable);
	//new Thread(futureTask).start();
	try {
		System.out.println(futureTask.get());
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}
}
