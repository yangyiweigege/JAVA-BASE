package com.weige.ssm.thread.safe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest extends RecursiveTask<Long>{
	
	private static final int THRESHOLD = 10000;
	private long start;
	private long end;
	
	public  ForkJoinTest() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Long compute() {
		long sum = 0;
		boolean canCompute = (end - start) < THRESHOLD;
		if (canCompute) {
			for (long i = start; i <= end; i++) {
				sum += i;
			}
		} else { //分解成100个小任务
			long step = (start + end) / 100;
			List<ForkJoinTest> subTasks = new ArrayList<ForkJoinTest>();
			long pos = start;
			for (int i = 0; i < 100; i++) {
				long lastOne = pos + step;
				if (lastOne > end)
					lastOne = end;
				//ForkJoinTest forkJoinTest = new  
			}
		}
		return null;
	}

}
