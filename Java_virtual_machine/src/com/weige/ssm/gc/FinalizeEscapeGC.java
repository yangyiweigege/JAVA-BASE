package com.weige.ssm.gc;


/**
 * 一次对象被回收之前 至少经历两次标记
 *  
 * @author yangyiwei
 * @date 2018年10月15日
 * @time 下午2:34:25
 */
public class FinalizeEscapeGC {

	public static FinalizeEscapeGC SAVE_HOOK = null;
	
	public void isAlive() {
		
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
	
	
	
}
