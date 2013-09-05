package com.riaval.matcalc.model;

public class Matrix {

	public static double getDeterminant(double[][] arr) {
		int length = arr.length;

		if (arr == null || length == 0 || length != arr[0].length) {
			throw new IllegalArgumentException("Wrong argument");
		}

		if (length == 1) {
			return arr[0][0];
		}

		if (length == 2) {
			return get2DimDet(arr);
		} else {
			return getDetLaplace(arr);
		}

	}

	private static double get2DimDet(double[][] arr) {
		int length = arr.length;

		if (arr == null || length == 0 || length != arr[0].length) {
			throw new IllegalArgumentException("Wrong argument");
		}
		double atr1 = arr[0][0] * arr[1][1];
		double atr2 = arr[0][1] * arr[1][0];
		return atr1 - atr2;
	}

	private static double getDetLaplace(double[][] arr) {
		int length = arr.length;

		if (arr == null || length < 3 || length != arr[0].length) {
			throw new IllegalArgumentException("Wrong argument");
		}

		double[][] newArr = new double[length - 1][length - 1];
		double all = 0;
		for (int i = 0; i < length; i++) {
			int result = (int) (arr[0][i] * Math.pow(-1, i + 2));

			for (int j = 1; j < length; j++) {
				int pr = 0;
				for (int k = 0; k < length; k++) {
					if (k == i) {
						if (pr == 0)
							pr++;
						continue;
					}
					newArr[j - 1][k - pr] = arr[j][k];
				} // k
			} // j
			all += result * getDeterminant(newArr);
		} // i
		return all;
	}

}
