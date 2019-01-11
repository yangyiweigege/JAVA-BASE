package com.weige.ssm.vali;

public class FinalClass {

	private FinalClass() {

	}

	private static class SingleResoure {

		private static FinalClass finalClass = new FinalClass();

		public static FinalClass getResource() {
			return finalClass;
		}
	}

	/*
	 * public static FinalClass getResource() { if (finalClass == null) {
	 * synchronized (FinalClass.class) { if (finalClass == null) { finalClass =
	 * new FinalClass(); } } } return finalClass; }
	 */

	public static FinalClass getResource2() {
		return SingleResoure.getResource();
	}

	public static void main(String[] args) {
		/*String string = new StringBuilder("计算机").append("软件").toString();

		System.out.println(string.intern() == string);*/
		String string1 = new String("j") + new String("a");
		System.out.println(string1.intern() == string1);
		String string2 = new String("v") + new String("a");
		System.out.println(string2.intern() == string2);
		

	}

}
