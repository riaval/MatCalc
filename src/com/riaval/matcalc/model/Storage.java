package com.riaval.matcalc.model;

public class Storage {

	private static double[][] mA;
	private static double[] mB;

	public static void setA(double[][] value) {
		mA = value;
	}
	public static double[][] getA() {
		return mA;
	}
	
	public static void setB(double[] answer) {
		mB = answer;
	}
	public static double[] getB() {
		return mB;
	}

}
