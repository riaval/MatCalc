package com.riaval.matcalc.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.riaval.matcalc.R;

public class ResultActivity extends Activity {
	
//	// TODO: delete this array
//	private int[][] arr = new int[][] {
//			  {3, 4, 5, 6, 7}
//			, {2, 3, 4, 9, 6}
//			, {1, 2, 3, 4, 5}
//			, {5, 2, 5, 7, 1}
//	};
	
	// TODO: delete this array
		private int[][] arr = new int[][] {
				  {21, 2, 3, 4}
				, {5, 6, 21, 8}
				, {9, 10, 11, 12}
				, {13, 14, 15, 16}
		};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		TextView textView = (TextView) findViewById(R.id.result_det);
		textView.setText( String.valueOf(count(arr)) );
		
	}

	private int count(int[][] arr) {
		int length = arr.length;
		int all = 0;
		
		if (length == 2){
			int atr1 = arr[0][0] * arr[1][1];
			int atr2 = arr[0][1] * arr[1][0];
			return atr1 - atr2;
		} else {
			int[][] newArr = new int[length-1][length-1];
			
			for (int i = 0; i < length; i++) {
				int result = (int) (arr[0][i] * Math.pow(-1, i+2));
				
				for (int j = 1; j < length; j++) {
					int pr = 0;
					for (int k = 0; k < length; k++) {
						if (k == i) {
							if (pr == 0)
								pr++;
							continue;
						}
						newArr[j-1][k-pr] = arr[j][k];
					} // k
				} // j
				
				all += result * count(newArr);
			} // i
		}
		
		return all;
	}
	
}
